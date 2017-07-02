import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.math.NumberUtils
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Function: 
  * #Author: john 
  * #Create: 2017 04 12 下午8:14 
  */
object ParquentReader {
  val APP_NAME: String = "reader"

//  val inpath: String = "/home/john/IdeaProjects/gaea_data/batch/data/event_records/input"
  val inpath: String = "/home/john/IdeaProjects/gaea_data/batch/data/output"

  def main(args: Array[String]): Unit = {
    var conf = new SparkConf()
    conf.setAppName(APP_NAME)
    if (System.getProperty("user.name").toString.equals("john")) {
      conf = new SparkConf().setAppName(APP_NAME).setMaster("local[3]");
    }
    val sc = new SparkContext(conf)

    val sQLContext = new SQLContext(sc)
    sQLContext.read.parquet(inpath).show()

/*
    val df = sQLContext.read.parquet(inpath)
    val cols = df.columns;
    val pubArray: Array[Array[String]] = Array.ofDim[String](cols.length, 2);
    for (i <- 0 to (cols.length - 1)) {
      var col = cols(i)
      col = if (col.equals("client_time_s")) "client_time_l" else col
      pubArray(i)(1) = StringUtils.substringAfterLast(col, "_")
      pubArray(i)(0) = StringUtils.substringBeforeLast(col, "_")//.replace("record__value__", StringUtils.EMPTY)
    }


    df.registerTempTable("a");
    val dfs = sQLContext.sql(s"select * from a")
    dfs.map(r => {
      val profile: JSONObject = new JSONObject()
      val properties: JSONObject = new JSONObject()
      for (i <- 0 until r.length - 1) {
        breakable {
          if (null == r || null == r(i))
            break()
          val key = pubArray(i)(0)
          val suffix = pubArray(i)(1)
          val value = this.convert(r(i).toString, suffix)
          key match {
            case "client_id" => profile.put("distinct_id", value)
            case "client_time" => profile.put("time", value)
            case _ => if (null != value) properties.put(key, value)
          }
        }
      }
      profile.put("properties", properties)
      profile
    }).foreach(f => println(f.toJSONString))*/
  }

  def convert(value: String, suffix: String = null): Any = suffix match {
    case "l" => if (NumberUtils.isNumber(value)) value.toLong else 0l
    case "i" => if (NumberUtils.isNumber(value)) value.toInt else 0
    case "d" => if (NumberUtils.isNumber(value)) value.toDouble else 0d
    case "f" => if (NumberUtils.isNumber(value)) value.toFloat else 0f
    case "b" => if (NumberUtils.isNumber(value)) value.toBoolean else false
    case _ => if (StringUtils.isBlank(value) || value.equals("\\N")) null else value
  }
}
