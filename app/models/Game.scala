package models

import java.sql.Date
import java.time.LocalDate

import scala.slick.lifted.Tag
import play.api.db.slick.Config.driver.simple._


case class Players(player_1: String, score_1: String, player_2: String, score_2: String)

case class Game(player_1: String, score_1: String, player_2: String, score_2: String, date : Date, id: Int){
//        val now = Date.valueOf(LocalDate.now())
}

class GameTable(tag: Tag) extends Table[Game](tag, "GAME") {
//  def players = column[Players]("players", O.NotNull)
  def player_1 = column[String]("player_1", O.NotNull)

  def score_1 = column[String]("score_1", O.NotNull)

  def player_2 = column[String]("player_2", O.NotNull)

  def score_2 = column[String]("score_2", O.NotNull)

  def date = column[String]("date", O.NotNull)

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def * = player_1, score_1, player_2, score_2, date, id.? <> (Game.tupled, Game.unapply _)

}
