package world.cepi.k.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.kstom.command.kommand.Kommand
import javax.script.*


object KCommand : Kommand({

    val args = ArgumentType.StringArray("args").map { it.joinToString(" ") }

    syntax(args) {

        val benchmark = System.currentTimeMillis()

        val engine = ScriptEngineManager().getEngineByExtension("kts")
        engine.setBindings(engine.createBindings(), ScriptContext.GLOBAL_SCOPE)
        engine.getBindings(ScriptContext.GLOBAL_SCOPE)["player"] = player
        val output = engine.eval(!args)
        val time = System.currentTimeMillis() - benchmark

        sender.sendMessage(
            Component.text("[${time / 1000.0}] ", NamedTextColor.DARK_GRAY)
                .append(Component.text(">> $output", NamedTextColor.GRAY))
        )
    }
}, "k")