import controllers.MySupportController
import org.scalatestplus.play._
import play.api.i18n._
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Unit tests can run without a full Play application.
  */
class MySupportControllerSpec extends PlaySpec {

  "MySupportController" should {

    "render home page" in {
      val langs = new DefaultLangs
      val controller = new MySupportController(stubControllerComponents(), langs)
      val result = controller.index(FakeRequest())
      status(result) must equal(OK)
    }

    "set new language in the cookie" in {
      val langs = new DefaultLangs
      val controller = new MySupportController(stubControllerComponents(), langs)
      val result = controller.homePageInFrench(FakeRequest())
      status(result) must equal(SEE_OTHER)
    }

    "clear language from the cookie" in {
      val langs = new DefaultLangs
      val controller = new MySupportController(stubControllerComponents(), langs)
      val result = controller.homePageWithDefaultLang(FakeRequest())
      status(result) must equal(SEE_OTHER)
    }
  }

}
