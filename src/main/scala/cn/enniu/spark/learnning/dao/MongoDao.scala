package cn.enniu.spark.learnning.dao

import cn.enniu.spark.learnning.Settings
import org.mongodb.scala.bson.BsonObjectId
import org.mongodb.scala.{Document, MongoClient}
import org.mongodb.scala.model.Filters.equal

/**
  * Created by xuwenjuan on 17/2/16.
  */
class MongoDao extends Dao {

  val mongoClient = MongoClient(Settings.get("mongodb.address"))
  val mongoDbName = Settings.get("mongodb.dbName")

  override def queryLabelById(labelFieldName: String, labelId: Long, collectionName: String): Seq[Document] = {
    val db = mongoClient.getDatabase(mongoDbName)
    val collection = db.getCollection(collectionName)
    var fieldValue: Any = labelId
    if ("_id".equals(labelFieldName)) {
      fieldValue = BsonObjectId(String.valueOf(labelId))
    }
    import cn.enniu.spark.learnning.utils.MongoHelper._
    collection.find(equal(labelFieldName, fieldValue)).results()
  }

}
