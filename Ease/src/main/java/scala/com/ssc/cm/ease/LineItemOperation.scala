package com.ssc.cm
package ease

trait LineItemOperation {
  self: Ease with MarginCallOperation =>

  val backToMarginCall = Click("CMWMCM_LINEITEM", "MCM_Maintain Line Item_Back") :: SendKey("{ENTER}") :: Nil

  def insertLineItem(marginCallTab: MarginCallTab, lineItemTab: LineItemTab, secType: SecurityType, sec: TypedCell, nom: TypedCell) = {
    enterMarginCallTab(marginCallTab) ::
      viewLineItem(marginCallTab) ::
      enterLineItemTab(lineItemTab) ::
      Click("CMWMCM_LINEITEM", "MCM_Maintain Line Item_Insert") ::
      selectOption("CMWMCM_LINEITEM", "MCM_Insert Line Item_SEC_TYPE_ID", (secType match {
        case Isin => 2
      })) :::
      Input("CMWMCM_LINEITEM", "MCM_Insert Line Item_SECURITY", sec) ::
      Input("CMWMCM_LINEITEM", "MCM_Insert Line Item_NOMINAL", nom) ::
      Click("CMWMCM_LINEITEM", "MCM_Insert Line Item_Do Insert") ::
      backToMarginCall ::: Nil
  }

  def modifyLineItem(marginCallTab: MarginCallTab, lineItemTab: LineItemTab) = {
    enterMarginCallTab(marginCallTab) ::
      viewLineItem(marginCallTab) ::
      enterLineItemTab(lineItemTab) ::
      Click("CMWMCM_LINEITEM", "MCM_Maintain Line Item_Modify") ::
      Click("CMWMCM_LINEITEM", "MCM_Insert Line Item_Do Modify") ::
      backToMarginCall ::: Nil
  }

  def sendLineItem(marginCallTab: MarginCallTab) = {
    enterMarginCallTab(marginCallTab) ::
      viewLineItem(marginCallTab) ::
      Click("CMWMCM_LINEITEM", "MCM_Maintain Line Item_Send") ::
      Click("CMWMCM_COMMEN", "MCM_Confirm_OK") ::
      backToMarginCall ::: Nil
  }

  def acceptLineItem(marginCallTab: MarginCallTab, lineItemTab: LineItemTab, ord: Int) = {
    enterMarginCallTab(marginCallTab) ::
      viewLineItem(marginCallTab) ::
      enterLineItemTab(lineItemTab) ::
      checkLineItem(ord) ::
      Click("CMWMCM_LINEITEM", "MCM_Maintain Line Item_Accept") ::
      Click("CMWMCM_LINEITEM", "MCM_Maintain Line Item_OK") ::
      backToMarginCall ::: Nil
  }

  def rejectLineItem(marginCallTab: MarginCallTab, lineItemTab: LineItemTab, rejectReason: RejectReason) = {
    enterMarginCallTab(marginCallTab) ::
      viewLineItem(marginCallTab) ::
      enterLineItemTab(lineItemTab) ::
      Click("CMWMCM_LINEITEM", "MCM_Maintain Line Item_Edit_Comment") ::
      selectOption("CMWMCM Item Edit Reject Comment", "Reject_CODE", rejectReason match {
        case Other(_) => 6
      }) :::
      Input("CMWMCM Item Edit Reject Comment", "Reject_comment", rejectReason.comment) ::
      Click("CMWMCM Item Edit Reject Comment", "Reject_save") ::
      Click("CMWMCM_LINEITEM", "MCM_Maintain Line Item_ROW_NUM") ::
      Click("CMWMCM_LINEITEM", "MCM_Maintain Line Item_Reject") ::
      Click("CMWMCM_COMMEN", "MCM_Confirm_OK") ::
      backToMarginCall ::: Nil
  }

  def completeLineItem(marginCallTab: MarginCallTab, lineItemTab: LineItemTab, ord: Int) = {
    enterMarginCallTab(marginCallTab) ::
      viewLineItem(marginCallTab) ::
      enterLineItemTab(lineItemTab) ::
      checkLineItem(ord) ::
      Click("CMWMCM_LINEITEM", "MCM_Maintain Line Item_Generate Instruction") ::
      Click("CMWMCM_COMMEN", "MCM_Confirm_OK") ::
      backToMarginCall ::: Nil
  }

  def cancelLineItem(marginCallTab: MarginCallTab, lineItemTab: LineItemTab, ord: Int) = {
    enterMarginCallTab(marginCallTab) ::
      viewLineItem(marginCallTab) ::
      enterLineItemTab(lineItemTab) ::
      checkLineItem(ord) ::
      Click("CMWMCM_LINEITEM", "MCM_Maintain Line Item_Cancel") ::
      Click("CMWMCM_COMMEN", "MCM_Confirm_OK") ::
      backToMarginCall ::: Nil
  }

  def viewLineItem(marginCallTab: MarginCallTab) = {
    marginCallTab match {
      case Overview => Click("CMWMCM_OVERVIEW", "MCM_Detail_Line Item")
      case Issue => Click("CMWMCM_ISSUE", "MCM_ISSUE_Line Item")
      case Agree => Click("CMWMCM_AGR", "MCM_AGREE_Line Item")
      case Dispute => Click("CMWMCM_DIS", "MCM_DISPUTE_Line Item")
      case Allocate => Click("CMWMCM_ALLO", "CMWMCM_ALLO_Line Item")
      case Complete => Click("CMWMCM_COMPLETE", "MCM_COMPLETE_Line Item")
    }
  }

  def enterLineItemTab(lineItemTab: LineItemTab) = {
    Click("CMWMCM_LINEITEM", lineItemTab match {
      case In => "MCM_Maintain Line Item_Link_Collateral In"
      case Out => "MCM_Maintain Line Item_Link_Collateral Out"
    })
  }

  def checkLineItem(ord: Int) = {
    Click("CMWMCM_LINEITEM", ord match {
      case 1 => "MCM_Maintain Line Item_ROW_NUM"
      case 2 => "MCM_Maintain Line Item_ROW_NUM_2"
      case 3 => "MCM_Maintain Line Item_ROW_NUM_3"
      case _ => throw new UnsupportedOperationException("checkbox object not ready")
    })
  }

  def deleteLineItem(marginCallTab: MarginCallTab, lineItemTab: LineItemTab, ord: Int) = {
    enterMarginCallTab(marginCallTab) ::
      viewLineItem(marginCallTab) ::
      enterLineItemTab(lineItemTab) ::
      checkLineItem(ord) ::
      Click("CMWMCM_LINEITEM", "MCM_Maintain Line Item_Delete") ::
      Click("CMWMCM_COMMEN", "MCM_Confirm_OK") ::
      backToMarginCall ::: Nil
  }

  def checkLineItemStaus(marginCallTab: MarginCallTab, lineItemTab: LineItemTab, status: TypedCell) = {
    enterMarginCallTab(marginCallTab) ::
      viewLineItem(marginCallTab) ::
      enterLineItemTab(lineItemTab) ::
      CaptureScreen ::
      Check("CMWMCM_LINEITEM", "MCM_Maintain Line Item_Status", status) ::
      backToMarginCall ::: Nil
  }

  sealed trait LineItemTab

  trait SecurityType

  trait RejectReason {
    def comment: TypedCell
  }

  case class Other(comment: TypedCell) extends RejectReason

  case object In extends LineItemTab

  case object Out extends LineItemTab

  case object Isin extends SecurityType

}