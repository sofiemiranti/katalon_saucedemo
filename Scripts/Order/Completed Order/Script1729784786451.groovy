import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('https://saucedemo.com')

WebUI.sendKeys(findTestObject('LoginPage/UsernameField'), 'standard_user')

WebUI.sendKeys(findTestObject('LoginPage/PasswordField'), 'secret_sauce')

WebUI.click(findTestObject('LoginPage/LoginButton'))

WebUI.verifyElementPresent(findTestObject('ProductPage/ProductLabel'), 10)

WebUI.selectOptionByLabel(findTestObject('ProductPage/Filter Dropdown (1)'), 'Price (low to high)', false)

String itemName1 = 'Sauce Labs Backpack'

WebUI.click(findTestObject('ProductPage/button_Add to cart', [('itemName') : itemName1]))

WebUI.verifyElementText(findTestObject('ProductPage/cart_number'), '1')

String itemName2 = 'Sauce Labs Bolt T-Shirt'

WebUI.click(findTestObject('ProductPage/button_Add to cart', [('itemName') : itemName2]))

WebUI.verifyElementText(findTestObject('ProductPage/cart_number'), '2')

WebUI.click(findTestObject('ProductPage/cart'))

WebUI.verifyElementPresent(findTestObject('CartPage/Your Cart Label'), 10)

WebUI.verifyElementText(findTestObject('CartPage/Item Name 1'), itemName1)

String priceItem1Text = WebUI.getText(findTestObject('CartPage/price_item1'))

// Remove unwanted characters and convert to BigDecimal for accurate calculations
BigDecimal priceItem1 = new BigDecimal(priceItem1Text.replaceAll('[^\\d.]', ''))

WebUI.verifyElementText(findTestObject('CartPage/Item Name 2'), itemName2)

String priceItem2Text = WebUI.getText(findTestObject('CartPage/price_item2'))

BigDecimal priceItem2 = new BigDecimal(priceItem2Text.replaceAll('[^\\d.]', ''))

BigDecimal totalPriceItemExpected = priceItem1 + priceItem2

WebUI.click(findTestObject('CartPage/Checkout Button'))

WebUI.verifyElementPresent(findTestObject('Checkout Page/Checkout Your Information Label'), 10)

WebUI.sendKeys(findTestObject('Checkout Page/First Name Field'), 'Sofie')

WebUI.sendKeys(findTestObject('Checkout Page/Last Name Field'), 'Miranti')

WebUI.sendKeys(findTestObject('Checkout Page/Postal Code Field'), '12345')

WebUI.click(findTestObject('Checkout Page/Continue Button'))

WebUI.verifyElementPresent(findTestObject('Checkout Overview Page/Checkout Overview Label'), 10)

String totalItemPriceText = WebUI.getText(findTestObject('Checkout Overview Page/Total Item Price'))

BigDecimal totalItemPrice = new BigDecimal(totalItemPriceText.replaceAll('[^\\d.]', ''))

WebUI.verifyEqual(totalItemPrice, totalPriceItemExpected)

String taxText = WebUI.getText(findTestObject('Checkout Overview Page/Tax'))

BigDecimal tax = new BigDecimal(taxText.replaceAll('[^\\d.]', ''))

String totalPriceText = WebUI.getText(findTestObject('Checkout Overview Page/Total Price'))

BigDecimal totalPrice = new BigDecimal(totalPriceText.replaceAll('[^\\d.]', ''))

BigDecimal totalPriceExpected = totalPriceItemExpected + tax

WebUI.verifyEqual(totalPrice, totalPriceExpected)

WebUI.click(findTestObject('Checkout Overview Page/Finish Button'))

WebUI.verifyElementPresent(findTestObject('Checkout Overview Page/Thank you for your order'), 10)

WebUI.closeBrowser()

