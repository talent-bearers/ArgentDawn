package talent.bearers.slimefusion.common.block

import talent.bearers.slimefusion.common.lib.LibNames

/**
 * @author WireSegal
 * Created at 11:00 PM on 12/23/16.
 */
object ModBlocks {
    val MARK: BlockMark
    val COLORFUL_LOG: BlockLogColorful
    val DARK_LOG: BlockLogDark
    val PLANKS: BlockPlanks
    val QUARTZITE: BlockStone
    val OPAL_QUARTZITE: BlockStone
    val SCORCHED_QUARTZITE: BlockStone
    val BLUEDUST: BlockZeleniDirt
    val SHALEDUST: BlockZeleniDirt
    val FLOWER: BlockZeleniFlower
    val ORE: BlockPolestoneOre

    init {
        MARK = BlockMark()
        COLORFUL_LOG = BlockLogColorful()
        DARK_LOG = BlockLogDark()
        PLANKS = BlockPlanks()
        QUARTZITE = BlockStone(LibNames.QUARTZITE)
        OPAL_QUARTZITE = BlockStone(LibNames.OPAL_QUARTZITE)
        SCORCHED_QUARTZITE = BlockStone(LibNames.SCORCHED_QUARTZITE)
        BLUEDUST = BlockZeleniDirt(LibNames.BLUEDUST)
        SHALEDUST = BlockZeleniDirt(LibNames.SHALEDUST)
        FLOWER = BlockZeleniFlower()
        ORE = BlockPolestoneOre()
    }
}
