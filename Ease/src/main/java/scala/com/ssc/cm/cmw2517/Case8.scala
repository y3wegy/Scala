package com.ssc.cm
package cmw2517

/**
 * *
 * sent unaccepted line item for client call
 * Action: Enter Margin Call Manager page.
 * Action: Create margin call with Counterparty Call as Call Type, Collateral Substitution as Call Business Type.
 * Action: Create one line item at each of In and Out tab.
 * Action: Issue created margin call if it is Client Call.
 * Action: Create line item at Out tab.
 * Action: Send line item.
 * Check:  A margin call with Pledge Generated as Line Item Group Status for Client Call or Pledged for Counterparty Call.
 * Action: Delete line item at In tab.
 * Action: Send line item.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 *
 */
object Case8 extends Ease with MarginCallOperation with LineItemOperation {

  val strUrl = "http://10.248.98.82:8881/CM_Portal/"
  val agrId = 77044
  val agrName = "CMW-2188-SWAP1"

  val actions = withDelay(login(nameOf(strUrl)) :::
    enterMarginCall :::
    filterMarginCall(nameOf(agrId)) :::
    addSubstitution(nameOf(agrName), ClientCall, ClientCollAccount) :::
    insertLineItem(Issue, In, Isin, "000000CCYUSD", 100) :::
    insertLineItem(Issue, Out, Isin, "000000CCYUSD", 100) :::
    issueMarginCall(1) :::
    agreeMarginCall :::
    insertLineItem(Issue, Out, Isin, "000000CCYUSD", 100) :::
    sendLineItem(Allocate) :::
    checkLineItemGroupStatus("Pledge Generated") :::
    deleteLineItem(Allocate, In, 1) :::
    sendLineItem(Allocate) :::
    checkLineItemGroupStatus("Pending Pledge") :::
    CloseBrowser :: Nil, 2)

  val data = named(strUrl) :: named(agrId) :: named(agrName) :: Nil

  val errorHandler = "Next_TestCase"

  val writeTo = "TestScripts"

}

/*user will not send unaccepted line item*/