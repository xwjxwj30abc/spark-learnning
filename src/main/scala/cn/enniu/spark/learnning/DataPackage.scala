package cn.enniu.spark.learnning


/**
  * Created by xuwenjuan on 17/2/16.
  */
class DataPackage(val labelId: String) extends Serializable {

  var mongoData: String = _

  override def toString: String = s"mongoData:${mongoData}"

  def toJson: String = {
    val sb = new StringBuilder
    sb.append("{\"mongoData\":}")
    sb.append(mongoData)
    sb.append("}")
    sb.toString()
  }
}
