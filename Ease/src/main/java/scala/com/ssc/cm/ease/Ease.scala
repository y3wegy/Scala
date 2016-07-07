package com.ssc.cm
package ease

import java.io.{File, FileOutputStream}

abstract class Ease {

  implicit val toTextCell: String => TextCell = TextCell
  implicit val toIntCell: Int => IntCell = IntCell

  def errorHandler: String

  def actions: List[Action]

  def data: List[(String, _)]

  def writeTo: String

  def main(args: Array[String]): Unit = {
    val myCaseBook = new HSSFWorkbook()
    myCaseBook.createSheet("Manual Steps")
    val myCaseSheet = myCaseBook.createSheet("Automation Script")
    myCaseSheet.setDefaultRowHeightInPoints(17)
    val myCaseHeaders = myCaseSheet.createRow(0)
    writeRow(myCaseHeaders,
      TextCell("Step Name") :: TextCell("Screen Name") :: TextCell("Object Name") :: TextCell("Action") :: TextCell("Parameter") :: TextCell("ErrorHandler") :: Nil,
      0)
    writeActions(myCaseSheet, actions, 1)
    myCaseSheet.autoSizeColumn(0)
    myCaseSheet.autoSizeColumn(1)
    myCaseSheet.autoSizeColumn(2)
    myCaseSheet.autoSizeColumn(3)
    myCaseSheet.autoSizeColumn(4)
    myCaseSheet.autoSizeColumn(5)
    val myCaseOut = new FileOutputStream(new File(new File(writeTo), "myCase.xls"))
    myCaseBook.write(myCaseOut)
    myCaseOut.close()

    val myDataBook = new HSSFWorkbook()
    val myDataSheet = myDataBook.createSheet("test")
    val myDataHeaders = myDataSheet.createRow(0)
    writeRow(myDataHeaders,
      TextCell("S.No") :: TextCell("TestName") :: (1 until 100).map(i => TextCell(s"Parameter$i")).toList,
      0)
    val myDataContent = myDataSheet.createRow(1)
    writeRow(myDataContent,
      BlankCell :: TextCell("myCase") :: data.map(t => TextCell(s"${t._1}=${t._2}")),
      0)
    val myDataOut = new FileOutputStream(new File(new File(writeTo), "myData.xls"))
    (0 until 102).foreach(i => myDataSheet.autoSizeColumn(i))
    myDataBook.write(myDataOut)
    myDataOut.close()
  }

  def selectOption(screen: String, obj: String, ord: Int): List[Action] = {
    val down = SendKey("{DOWN}")
    Click(screen, obj) :: (0 until ord).toList.map(_ => down)
  }

  def selectSuggest(screen: String, obj: String, parameter: TypedCell): List[Action] = {
    Click(screen, obj) ::
      Input(screen, obj, parameter) ::
      Click(screen, obj) ::
      SendKey("{DOWN}") ::
      SendKey("{ENTER}") :: Nil
  }

  def login(strURL: TextCell) = Call("LAUNCH", strURL) ::
    Wait(3) ::
    SendKey("%{{TAB}") ::
    SendKey("%{{ESC}") ::
    Click("Login", "btn_login_accept") :: Nil

  def withDelay(actions: List[Action], seconds: Int): List[Action] = {
    val wait = Wait(seconds)
    @tailrec
    def rec(actions: List[Action], ret: List[Action]): List[Action] = {
      if (actions.isEmpty) ret.reverse
      else {
        val action = actions.head
        if (actions.tail.isEmpty) {
          (action :: ret).reverse
        } else {
          val nextAction = actions.tail.head
          action match {
            case Wait(_) => rec(actions.tail, action :: ret)
            case _ => nextAction match {
              case Wait(_) => rec(actions.tail, action :: ret)
              case _ => rec(actions.tail, wait :: action :: ret)
            }
          }
        }

      }
    }

    rec(actions, Nil)
  }

  @tailrec
  private[this] def writeActions(sheet: Sheet, actions: List[Action], indx: Int): Unit = {
    if (actions.isEmpty) ()
    else {
      val action = actions.head
      val applied = TextCell(action.name) :: (action match {
        case p: Parametric => p.parameter
        case _ => BlankCell
      }) :: TextCell(errorHandler) :: Nil
      val row = BlankCell :: (action match {
        case t: TargetUnit => TextCell(t.screen) :: TextCell(t.obj) :: applied
        case _ => BlankCell :: BlankCell :: applied
      })
      val r = sheet.createRow(indx)
      writeRow(r, row, 0)
      writeActions(sheet, actions.tail, indx + 1)
    }
  }

  @tailrec
  private[this] def writeRow(row: Row, cells: List[TypedCell], indx: Int): Unit = {
    if (cells.isEmpty) ()
    else {
      val cell = cells.head
      cell match {
        case BlankCell => {
          row.createCell(indx, Cell.CELL_TYPE_BLANK)
        }
        case TextCell(str) => {
          val c = row.createCell(indx, Cell.CELL_TYPE_STRING)
          c.setCellValue(str)
        }
        case IntCell(i) => {
          val c = row.createCell(indx, Cell.CELL_TYPE_NUMERIC)
          c.setCellValue(i)
        }
      }
      writeRow(row, cells.tail, indx + 1)
    }
  }

  sealed trait TypedCell

  sealed trait StyledCell

  sealed trait Action {
    def name: String
  }

  trait Parametric {
    self: Action =>
    def parameter: TypedCell
  }

  trait TargetUnit {
    self: Action =>
    def screen: String

    def obj: String
  }

  case class TextCell(value: String) extends TypedCell

  case class IntCell(value: Int) extends TypedCell

  case class Click(screen: String, obj: String) extends Action with TargetUnit {
    override val name = "CLICK"
  }

  case class Input(screen: String, obj: String, parameter: TypedCell) extends Action with Parametric with TargetUnit {
    override val name = "INPUT"
  }

  case class Wait(parameter: TypedCell) extends Action with Parametric {
    override val name = "WAIT"
  }

  case class SendKey(parameter: TypedCell) extends Action with Parametric {
    override val name = "SENDKEYS"
  }

  case class Check(screen: String, obj: String, parameter: TypedCell) extends Action with TargetUnit with Parametric {
    override val name = "VALIDATE"
  }

  case class GetValue(screen: String, obj: String, parameter: TypedCell) extends Action with TargetUnit with Parametric {
    override val name = "RETRIEVEVALUE"
  }

  case class Call(name: String, parameter: TypedCell) extends Action with Parametric

  case object BlankCell extends TypedCell

  case object CaptureScreen extends Action {
    override val name = "CAPTURESCREEN"
  }

  case object CloseBrowser extends Action {
    override val name = "CLOSEBROWSER"
  }

}