package com.gatling.demo.gatling.util

object TargetsIoClient {

  def sendTestRunEvent(host: String, command: String, testRunId: String, buildResultKey: String, dashboardName: String, productName: String ) {

    println( "sending "+ command + " test run call to rest service at host " + host + " with data: testRunId: "+ testRunId + ", productName: " + productName + ", dashboardName: " + dashboardName +  ", buildResultKey " + buildResultKey )

    val runningTestUrl = host + "/running-test/" + command

    val runningTest = new targetsIoRunningTest(productName, dashboardName, testRunId, buildResultKey)

    try {
      val response = TargetsIoTestRunClient.runningTestCall(runningTestUrl, runningTest)
      println( "responseCode: "+ response )
      if ( response != 200 ) {

        println( "Something went wrong when starting the tesr run, please check in your pom.xml if the test run ID is unique for this dashboard. If so please contact support." )

      }
    } catch {
      case e: Exception =>
        println ( "exception occured: " + e + ", please create test run manually" );
    }

  }

}

class targetsIoRunningTest( var productName: String, var dashboardName: String, var testRunId: String, var buildResultKey: String ) {
}
