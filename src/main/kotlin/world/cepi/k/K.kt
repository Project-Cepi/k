package world.cepi.k

import net.minestom.server.extensions.Extension;
import world.cepi.k.commands.KCommand

class K : Extension() {

    override fun initialize() {

        KCommand.register()

        logger.info("[K] has been enabled!")
    }

    override fun terminate() {

        KCommand.unregister()

        logger.info("[K] has been disabled!")
    }

}
