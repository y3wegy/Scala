package com.ssc.cm
package cmw2517

/**
 * *
 * single size line item
 * Action: Enter Margin Call Manager page.
 * Action: Create margin call with Collateral Substitution as Call Business Type.
 * Action: Create a line item.
 * Action: Issue margin call if it is clien call.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 * Action: Reject line item.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 * Action: Modify line item.
 * Action: Accept line item.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 * Action: Agree margin call.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 * Action: Complete line item.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 *
 */
object Case10 extends Ease with MarginCallOperation with LineItemOperation {

  val strUrl = "http://10.248.98.82:8881/CM_Portal/"
  val agrId = 77044
  val agrName = "CMW-2188-SWAP1"

  val actions = withDelay(login(nameOf(strUrl)) :::
    enterMarginCall :::
    filterMarginCall(nameOf(agrId)) :::
    addSubstitution(nameOf(agrName), ClientCall, ClientCollAccount) :::
    insertLineItem(Issue, In, Isin, "000000CCYUSD", 100) :::
    checkLineItemGroupStatus("Pending Pledge") :::
    issueMarginCall(1) :::
    rejectLineItem(Agree, In, Other("test")) :::
    checkLineItemGroupStatus("Pending Pledge") :::
    modifyLineItem(Agree, In) :::
    acceptLineItem(Agree, In, 1) :::
    checkLineItemGroupStatus("Pending Pledge") :::
    agreeMarginCall :::
    completeLineItem(Allocate, In, 1) :::
    checkLineItemGroupStatus("Pending Pledge") :::
    CloseBrowser :: Nil, 2)

  val data = named(strUrl) :: named(agrId) :: named(agrName) :: Nil

  val errorHandler = "Next_TestCase"

  val writeTo = "TestScripts"

}