package gocalc

import io.gatling.core.Predef._ 
import io.gatling.http.Predef._
import scala.concurrent.duration._

class Stresser extends Simulation{
    val httpProtocol = http.baseUrl("http://localhost:8083").acceptHeader("application/json")
    
    object Sum {
        
        val execute = exec(http("Sum #1")
            .get("/calc/sum/1/2"))
            .pause(2)
        .exec(http("Sum #2")
            .get("/calc/sum/3/4"))
            .pause(2)
        .exec(http("Sum #3")
            .get("/calc/sum/7/8"))
            .pause(2)
        .exec(http("History Endpoint")
            .get("/calc/history"))        
    }

    object Sub {
        val execute = exec(http("Sub #1")
            .get("/calc/sub/1/2"))
            .pause(3)
        .exec(http("Sub #2")
            .get("/calc/sub/3/4"))
            .pause(2)
        .exec(http("Sub #3")
            .get("/calc/sub/7/8"))
            .pause(2)
        .exec(http("History Endpoint")
            .get("/calc/history"))
    }

    object Mul {
        val execute = exec(http("Mul #1")
            .get("/calc/multiply/1/2"))
            .pause(1)
        .exec(http("Mul #2")
            .get("/calc/multiply/3/4"))
            .pause(1)
        .exec(http("Mul #3")
            .get("/calc/multiply/7/8"))
            .pause(2)
        .exec(http("History Endpoint")
            .get("/calc/history"))
    }

    object Div {
        val execute = exec(http("Div #1")
            .get("/calc/division/7/2"))
            .pause(2)
        .exec(http("Div #2")
            .get("/calc/division/10/4"))
            .pause(2)
        .exec(http("Division #3")
            .get("/calc/sum/20/8"))
            .pause(2)
        .exec(http("History Endpoint")
            .get("/calc/history"))
    }        
    
    val scn = scenario("Simple Stressing").exec(Sum.execute, Sub.execute, Div.execute, Mul.execute)    
    setUp(scn.inject(constantUsersPerSec(3).during(60 seconds)).protocols(httpProtocol))
}