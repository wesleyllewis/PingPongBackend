package controllers

import java.util.Calendar

import models._
import play.api._
import play.api.db.slick._
import play.api.db.slick.Config.driver.simple._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import play.api.Play.current
import play.api.mvc.BodyParsers._
import play.api.libs.json.Json
import play.api.libs.json.Json._

object Application extends Controller{

  //create an instance of the table]
  val games = TableQuery[GamesTable]


  //JSON read/write macro
  implicit val playersFormat = Json.format[Players]
  implicit val gameFormat = Json.format[Game]


  def jsonFindAll = DBAction { implicit rs =>
    Ok(toJson(games.list))
  }

  def jsonInsert = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Players].map { players =>
      val game = Game(players.player_1,players.player_2,players.score_1,players.score_2, Calendar.getInstance().getTime.toString)
      games.insert(game)
        Ok(toJson(game))
    }.getOrElse(BadRequest("invalid json"))    
  }

}
