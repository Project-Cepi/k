package world.cepi.k.commands

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.kstom.command.kommand.Kommand
import javax.script.ScriptContext
import javax.script.ScriptEngineManager

object KCommand : Kommand({

    val args = ArgumentType.StringArray("args").map { it.joinToString(" ") }

    syntax(args) {
        val engine = ScriptEngineManager().getEngineByExtension("kts")
        engine.setBindings(engine.createBindings(), ScriptContext.GLOBAL_SCOPE)
        engine.getBindings(ScriptContext.GLOBAL_SCOPE)["player"] = player
        engine.eval(!args)
    }
}, "k")