package org.example.server

import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent
import net.minestom.server.extras.MojangAuth
import net.minestom.server.instance.LightingChunk
import net.minestom.server.instance.block.Block
import net.minestom.server.utils.chunk.ChunkSupplier

fun main() {
    val server = MinecraftServer.init()
    val eventHandler = MinecraftServer.getGlobalEventHandler()
    val instanceManager = MinecraftServer.getInstanceManager()

    // Creating a world
    val instance = instanceManager.createInstanceContainer()
    instance.setChunkSupplier { _, x, z -> LightingChunk(instance, x, z) }
    instance.setGenerator { it.modifier().fillHeight(-64, 0, Block.STONE) }

    instance.time = 6000
    instance.timeRate = 0

    // Set the player's spawning instance when they join
    eventHandler.addListener(AsyncPlayerConfigurationEvent::class.java) { event ->
        event.spawningInstance = instance
        event.player.respawnPoint = Pos.ZERO
    }

    // Enable online mode and start the server
    MojangAuth.init()
    server.start("0.0.0.0", 25565)
}