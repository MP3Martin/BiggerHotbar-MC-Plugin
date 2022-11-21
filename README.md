# BiggerHotbar-MC-Plugin
Minecraft plugin that makes your hotbar bigger (using scrolling)

## Installation
* **Download** latest release from **[releases](https://github.com/MP3Martin/BiggerHotbar-MC-Plugin/releases)**
* Put the `.jar` file in your **plugins folder**
* **Restart** your server
* Type `/bh enable` to enable the plugin

## Version
This plugin should be on **1.12** - **∞**, *but it was tested only on 1.14.4*

## Permissions
* `biggerhotbar.hotbar`
  * Enables bigger hotbar <br><br>
* `biggerhotbar.commands`
  * Enables using BiggerHotbar's commands
  
> **Note**: Being OP allows you all of the above

## config.yml
* `bh_enabled`
  * is **boolean**
  * Default value: `false`
  * Controls if the plugin is doing it's main thing <br><br>
* `maxMovesAtOnce`
  * is **integer**
  * Default value: `4`
  * Controls the limit of how many scrolls can happen at one time (noticeable when using number keys to switch slots) <br><br>
* `mode`
  * is **string**
  * Default value: `center`
  * Controls the mode. Available options: `center` and `sides`
  
> **Note**: Reload config using `/bh reload`

<br><br>

---

<br>

## Todo
* add mode that scrolls only if you reach the end of hotbar
