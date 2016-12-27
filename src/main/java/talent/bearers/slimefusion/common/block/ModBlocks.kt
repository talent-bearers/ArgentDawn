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

    init {
        MARK = BlockMark()
        COLORFUL_LOG = BlockLogColorful()
        DARK_LOG = BlockLogDark()
        PLANKS = BlockPlanks()
        QUARTZITE = BlockStone(LibNames.QUARZITE)
        OPAL_QUARTZITE = BlockStone(LibNames.OPAL_QUARZITE)
        SCORCHED_QUARTZITE = BlockStone(LibNames.SCORCHED_QUARZITE)
    }
}
