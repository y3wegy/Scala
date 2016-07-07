package com.ssc.cm
package ease

trait MarginCallOperation {
  self: Ease =>

  val enterMarginCall = Click("CMW_MainMenu", "link_mainMenu_CMWHome") ::
    Click("CMWSuite", "Link_Suite_CollateralMgmDesk") ::
    Click("CMWSuite", "Link_MarginCallManagement") :: Nil
  val agreeMarginCall = enterMarginCallTab(Agree) ::
    GetValue("CMWMCM_AGR", "MCM_AGREE_Call Amount", "cpAmount") ::
    Click("CMWMCM_AGR", "MCM_AGREE_COUNTERPARTY_AMOUNT") ::
    Input("CMWMCM_AGR", "MCM_AGREE_COUNTERPARTY_AMOUNT", "cpAmount") ::
    Click("CMWMCM_AGR", "MCM_AGREE_COUNTERPARTY_AMOUNT") ::
    SendKey("{ENTER}") ::
    Click("CMWMCM_AGR", "MCM_AGREE_Agree") ::
    Click("CMWMCM_AGR", "MCM_AGREE_Confirm") ::
    Click("CMWMCM_COMMEN", "MCM_Confirm_OK") :: Nil

  def filterMarginCall(agrId: TypedCell) = Input("CMWMCM_FILTER", "MCM_Filter_AGR_ID", agrId) ::
    Click("CMWMCM_FILTER", "MCM_Filter_Save Settings") :: Nil

  def addSubstitution(agrName: TypedCell, callType: CallType, callAccount: CallAccount) = {
    Click("CMWMCM_OVERVIEW", "MCM_Detail_Add New") ::
      selectSuggest("CMWMCM_OVERVIEW", "MCM_Detail_Add New_Edit_Agreement", agrName) :::
      selectOption("CMWMCM_OVERVIEW", "MCM_Detail_Add New_Btn_List_Account", callAccount match {
        case ClientCollAccount => 1
        case CpCollAccount => 2
      }) :::
      selectOption("CMWMCM_OVERVIEW", "MCM_Detail_Add New_Btn_List_Call Type", callType match {
        case ClientCall => 1
        case CounterpartyCall => 2
      }) :::
      selectOption("CMWMCM_OVERVIEW", "MCM_Detail_Add New_Btn_List_Call Business Type", 2) :::
      Click("CMWMCM_OVERVIEW", "MCM_Detail_Add New_Btn_Do Insert") ::
      Nil
  }

  def issueMarginCall(callAmount: TypedCell) = enterMarginCallTab(Issue) ::
    Input("CMWMCM_ISSUE", "MCM_ISSUE_CALL_AMOUNT", callAmount) ::
    Click("CMWMCM_ISSUE", "MCM_ISSUE_Verified") ::
    Click("CMWMCM_ISSUE", "MCM_ISSUE_Confirm") ::
    Click("CMWMCM_COMMEN", "MCM_Confirm_OK") :: Nil

  def checkLineItemGroupStatus(status: TypedCell) = {
    enterMarginCallTab(Overview) ::
      CaptureScreen ::
      Check("CMWMCM_OVERVIEW", "MCM_Detail_Line Item_group_status", status) :: Nil
  }

  def enterMarginCallTab(marginCallTab: MarginCallTab) = {
    Click("CMWMCM_COMMEN", marginCallTab match {
      case Overview => "MCM_COM_Overview"
      case Issue => "MCM_COM_Issue"
      case Agree => "MCM_COM_Agree"
      case Dispute => "MCM_COM_Dispute"
      case Allocate => "MCM_COM_Allocate"
      case Complete => "MCM_COM_Complete"
    })
  }

  sealed trait CallType

  sealed trait CallAccount

  sealed trait MarginCallTab

  case object ClientCall extends CallType

  case object CounterpartyCall extends CallType

  case object ClientCollAccount extends CallAccount

  case object CpCollAccount extends CallAccount

  case object Overview extends MarginCallTab

  case object Issue extends MarginCallTab

  case object Agree extends MarginCallTab

  case object Dispute extends MarginCallTab

  case object Allocate extends MarginCallTab

  case object Complete extends MarginCallTab
}