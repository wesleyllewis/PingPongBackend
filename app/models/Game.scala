package models

import java.util.Calendar

import scala.slick.lifted.Tag
import play.api.db.slick.Config.driver.simple._
/**
  * Created by jakefroeb on 10/25/16.
  */
//case class Player(name : String, score: String)

case class Players(player_1 :String, score_1: String, player_2 : String, score_2:String)

case class Game(player_1 :String, player_2:String,  score_1: String,  score_2:String, date: String)

class GamesTable(tag: Tag) extends Table[Game](tag, "games") {
  def player1 = column[String]("player_1", O.NotNull)
  def player2 = column[String]("player_2", O.NotNull)
  def score1 = column[String]("score_1", O.NotNull)
  def score2 = column[String]("score_2", O.NotNull)
  def date = column[String]("date", O.NotNull)


  def * = (player1,player2,score1,score2,date) <> (Game.tupled, Game.unapply _)
}
