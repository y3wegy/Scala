package com.ssc.cm
package cmw2518

/**
 * *
 * modify Pledged Sent margin call.
 * Action: Enter Margin Call Manager page.
 * Action: Create margin call with Collateral Substitution as Call Business Type, Client Call as Call Type.
 * Action: Create line item at each of In and Out tab.
 * Action: Issue margin call.
 * Action: Modify line item at In tab.
 * Check:  A margin call with Pledge Generated as Line Item Group Status for Client Call.
 * Action: Create margin call with Collateral Substitution as Call Business Type, Client Call as Call Type.
 * Action: Create line item at each of In and Out tab.
 * Action: Issue margin call.
 * Action: Modify line item at Out tab.
 * Check:  A margin call with Pledge Generated as Line Item Group Status for Client Call.
 *
 */
object Case6 extends Ease with MarginCallOperation with LineItemOperation {

  val strUrl = "http://10.248.98.82:8881/CM_Portal/"
  val agrId = 77044
  val agrName = "CMW-2188-SWAP1"

  val actions = withDelay(login(nameOf(strUrl)) :::
    enterMarginCall :::
    filterMarginCall(nameOf(agrId)) :::
    addSubstitution(nameOf(agrName), ClientCall, ClientCollAccount) :::
    insertLineItem(Issue, In, Isin, "000000CCYUSD", 100) :::
    insertLineItem(Issue, Out, Isin, "000000CCYUSD", 100) :::
    enterMarginCallTab(Issue) ::
    issueMarginCall(1) :::
    modifyLineItem(Agree, In) :::
    checkLineItemGroupStatus("Pledge Generated") :::
    addSubstitution(nameOf(agrName), ClientCall, ClientCollAccount) :::
    insertLineItem(Issue, In, Isin, "000000CCYUSD", 100) :::
    insertLineItem(Issue, Out, Isin, "000000CCYUSD", 100) :::
    enterMarginCallTab(Issue) ::
    issueMarginCall(1) :::
    modifyLineItem(Agree, Out) :::
    CloseBrowser :: Nil, 2)

  val data = named(strUrl) :: named(agrId) :: named(agrName) :: Nil

  val errorHandler = "Next_TestCase"

  val writeTo = "TestScripts"

}

/*pledged sent*/