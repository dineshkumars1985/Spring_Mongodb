package com.rest.app.dao;

import com.mongodb.*;
import com.rest.app.domain.IexFetchModel;
import com.rest.app.domain.IexModel;
import com.rest.app.util.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Dinesh Kumar
 */
public class IexDao {

  private static final Logger logger = LoggerFactory.getLogger(IexDao.class);

  private MongoClient mongoClient;
  private String dbName;
  private String collectionName;
  private DBCollection iexCollection;

  public IexDao() {
  }

  public void init() throws UnknownHostException {
    DB iexDatabase = mongoClient.getDB(dbName);
    iexCollection = iexDatabase.getCollection(collectionName);
  }

  public Optional<IexModel> create(IexModel iexModel){
    try {
      iexCollection.insert(AppUtils.toDBObject(iexModel));
      logger.info("Added new company detail", iexModel);
      return Optional.of(iexModel);
    } catch (MongoException e) {
      logger.info("Error while insert", iexModel.getName());
      return Optional.empty();
    }
  }

  public List<IexModel> fetch(IexFetchModel iexFetch){
    DBObject query = new BasicDBObject();
    final List<IexModel> iexModels = new ArrayList<IexModel>();
    DBObject condition;

    DBObject iexIn = new BasicDBObject(1);
    iexIn.put("$in", iexFetch.getSymbol());
    query.put("_id.symbol",iexIn);

    if(iexFetch.getFrom_dt() != null &&  iexFetch.getTo_dt() != null){
      condition = new BasicDBObject(2);
      condition.put("$gte", AppUtils.changeTsFormate(iexFetch.getFrom_dt()));
      condition.put("$lte", AppUtils.changeTsFormate(iexFetch.getTo_dt()));
      query.put("_id.insert_ts", condition);
    }
    try (DBCursor cursor = iexCollection.find(query)) {
      while (cursor.hasNext()) {
        DBObject dbObject = cursor.next();
        iexModels.add((IexModel) AppUtils.fromDBObject(dbObject, IexModel.class));
      }
    }
    return iexModels;
  }

  public void setMongoClient(final MongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  public void setDbName(final String dbName) {
    this.dbName = dbName;
  }

  public void setCollectionName(final String collectionName) {
    this.collectionName = collectionName;
  }

}
