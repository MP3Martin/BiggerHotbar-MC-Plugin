package xyz.mp3martin.biggerhotbar.biggerhotbar.utils

object Settings {

  private const val PREFIX =  "§7[§6BiggerHotbar§7]"

  private const val MAIN_MESSAGE_COLOR = "§6"

  fun formatMessage(message: String): String {
    var output = "$PREFIX $MAIN_MESSAGE_COLOR$message"
    output = output.replace("§r", "§r$MAIN_MESSAGE_COLOR")
    return output
  }
}