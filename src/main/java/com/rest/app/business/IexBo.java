package com.rest.app.business;

import com.rest.app.dao.IexDao;
import com.rest.app.domain.*;
import com.rest.app.util.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

/**
 * Bussiness Layer
 * @author DineshKumar
 */
public class IexBo {
    private static final Logger logger = LoggerFactory.getLogger(IexBo.class);

    private IexDao iexDao;
    private String symbol;
    private Long iexinterval;

    @Autowired
    public IexBo(IexDao iexDao) {
        this.iexDao = iexDao;
    }

    public IexBo(){

    }

    /**
     *
     * @param iexFetch
     * @return
     */
    public List<IexResultModel> fetch(IexFetchModel iexFetch){
        List<IexModel> models = iexDao.fetch(iexFetch);
        List<IexResultModel> result = new ArrayList<IexResultModel>();
        if(!models.isEmpty()){
            Map<String,IexResultModel> iexMap = new HashMap<String,IexResultModel>();
            for(IexModel model : models){
                IexResultModel resModel;
                if(iexMap.containsKey(model.getName())){
                    resModel = iexMap.get(model.getName());
                }else{
                    resModel = new IexResultModel(model.getName(),model.getLogo());
                }
                resModel.addSingleObject(new IexTimeValue(AppUtils.changeTsFormate(model.get_id().getInsert_ts()), model.getPrice()));
                iexMap.put(model.getName(), resModel);
            }
            result.addAll(iexMap.values());
        }
        return result;
    }

    /**
     * Pull data from external source
     */
    public void pullIexDetails(){
        try{
            String[] symbols = symbol.split(",");
            for (String symb:symbols) {
               Double price =  new Double(AppUtils.getHttpResponse
                       ("https://api.iextrading.com/1.0/stock/".concat(symb).concat("/price")));

               String companyDetails = AppUtils.getHttpResponse
                       ("https://api.iextrading.com/1.0/stock/".concat(symb).concat("/company"));
               String companyName = AppUtils.getStringFromJson(companyDetails, "companyName");

               String logo = AppUtils.getHttpResponse
                       ("https://api.iextrading.com/1.0/stock/".concat(symb).concat("/logo"));
                IexPk pk = new IexPk(symb, AppUtils.getCurrentTimeStamp());
                IexModel imodel = new IexModel(pk, companyName, price, logo);
                iexDao.create(imodel);
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    /**
     * Scheduler
     */
    public void scheduleIexJob() {
            TimerTask repeatedTask = new TimerTask() {
                public void run() {
                    pullIexDetails();
                }
            };
            Timer timer = new Timer("IexTimer");

            long delay  = 1000L;
            long period = iexinterval;
            timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    public void setSymbol(final String symbol) {this.symbol = symbol;}
    public void setIexinterval(final Long iexinterval) {this.iexinterval = iexinterval;}

}
