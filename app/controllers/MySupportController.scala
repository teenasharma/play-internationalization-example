package controllers

import javax.inject._
import play.api.i18n._
import play.api.mvc._

class MySupportController @Inject()(component: ControllerComponents,
                                    langs: Langs) extends AbstractController(component) with I18nSupport {
  val lang: Lang = langs.availables.head
  implicit val messages: Messages = MessagesImpl(lang, messagesApi)

  def index = Action { implicit request =>
    val messages: Messages = messagesApi.preferred(request) // get the messages for the given request
  val message: String = messages("default.message")
    Ok(views.html.index(message))
  }

  def homePageInFrench = Action {
    Redirect("/").withLang(Lang("fr")) // set french language in the Play's language cookie for future requests
  }

  def homePageWithDefaultLang = Action {
    Redirect("/").clearingLang // discarding the language cookie set by withLang
  }
}
