package com.ssc.cm
package cmw2518

/**
 * *
 * modify rejected line item
 * Action: Enter Margin Call Manager page.
 * Action: Create margin call with Collateral Substitution as Call Business Type.
 * Action: Create line item at each of In and Out tab.
 * Action: Issue created margin call if it is Client Call.
 * Action: Accept both line items.
 * Action: Modify line item at In tab.
 * Check:  A margin call with Pledge Accepted as Line Item Group Status.
 * Check:  Line item at In tab with Pending Accepted as Status.
 * Action: Modify line item at Out tab.
 * Check:  A margin call with Pledge Modified as Line Item Group Status.
 * Check:  Line item at Out tab with Pending Accepted as Status.
 *
 */
object Case1 extends Ease with MarginCallOperation with LineItemOperation {

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
    rejectLineItem(Agree, In, Other("test")) :::
    rejectLineItem(Agree, Out, Other("test")) :::
    modifyLineItem(Agree, In) :::
    checkLineItemStaus(Agree, In, "Pending Accepted") :::
    checkLineItemGroupStatus("Pledge Rejected") :::
    modifyLineItem(Agree, Out) :::
    checkLineItemStaus(Agree, Out, "Pending Accepted") :::
    checkLineItemGroupStatus("Pledge Modified") :::
    CloseBrowser :: Nil, 2)

  val data = named(strUrl) :: named(agrId) :: named(agrName) :: Nil

  val errorHandler = "Next_TestCase"

  val writeTo = "TestScripts"

}