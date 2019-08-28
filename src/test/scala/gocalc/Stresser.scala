package gocalc

import io.gatling.core.Predef._ 
import io.gatling.http.Predef._
import scala.concurrent.duration._

class Stresser extends Simulation{

   val httpProtocol = http.baseUrl("http://localhost:8083").acceptHeader("application/json")

    val scenery = scenario("History Stressing")
        .exec(http("Sum #1")
            .get("/calc/sum/1/2"))
        .exec(http("Sum #2")
            .get("/calc/sum/3/4"))
        .exec(http("Sum #3")
            .get("/calc/sum/7/8"))
        .pause(1000 milliseconds)
        .exec(http("Acessing history")
            .get("/calc/history"))

        .exec(http("Sub #1")
            .get("/calc/sub/1/2"))
        .exec(http("Sub #2")
            .get("/calc/sub/3/4"))
        .exec(http("Sub #3")
            .get("/calc/sub/7/8"))
        .pause(1000 milliseconds)
        .exec(http("Acessing history")
            .get("/calc/history"))

        .exec(http("Mul #1")
            .get("/calc/multiply/1/2"))
        .exec(http("Mul #2")
            .get("/calc/multiply/3/4"))
        .exec(http("Mul #3")
            .get("/calc/multiply/7/8"))
        .pause(1000 milliseconds)
        .exec(http("Acessing history")
            .get("/calc/history"))

        .exec(http("Div #1")
            .get("/calc/division/7/2"))
        .exec(http("Div #2")
            .get("/calc/division/10/4"))
        .exec(http("Division #3")
            .get("/calc/sum/20/8"))
        .pause(1000 milliseconds)
        .exec(http("Acessing history")
            .get("/calc/history"))
    setUp(scenery.inject(constantUsersPerSec(3).during(60 seconds)).protocols(httpProtocol))
}