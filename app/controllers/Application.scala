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

object Application extends Controller {


  val games = TableQuery[GameTable]

  implicit val gameFormat = Json.format[Game]
  implicit val playerFormat = Json.format[Players]

  def index = DBAction { implicit rs =>
    Ok(views.html.index(games.list))
  }

  val gameForm = Form(
    mapping(
      "player_1" -> text(),
      "score_1" -> text(),
      "player_2" -> text(),
      "score_2" -> text(),
      "date" -> sqlDate,
      "id" -> number
    )(Game.apply)(Game.unapply)
  )

  def insert = DBAction { implicit rs =>
    val game = gameForm.bindFromRequest.get
    games.insert(game)

    Redirect(routes.Application.index)
  }

  def jsonFindAll = DBAction { implicit rs =>
    Ok(toJson(games.list))
  }

  def jsonInsert = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Game].map { game =>
      games.insert(game)
      Ok(toJson(game))
    }.getOrElse(BadRequest("invalid json"))
  }

}
