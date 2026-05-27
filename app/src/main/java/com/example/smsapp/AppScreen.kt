package com.example.smsapp

sealed class AppScreen(
    val route: String,
    val title: String
) {
    // Send Screens
    object SendV1 : AppScreen("send_v1", "Send V1")
    object SendV2 : AppScreen("send_v2", "Send V2")
    object SendV3 : AppScreen("send_v3", "Send V3")
    object SendV4 : AppScreen("send_v4", "Send V4")

    // Inbox Screens
    object InboxV1 : AppScreen("inbox_v1", "Inbox V1")
    object InboxV2 : AppScreen("inbox_v2", "Inbox V2")

    // Inbox Screens for Incoming
    object InboxIncomingV8 : AppScreen("inbox_incoming_v8", "V8")
    object InboxIncomingV9 : AppScreen("inbox_incoming_v9", "V9")
    object InboxIncomingV10 : AppScreen("inbox_incoming_v10", "V10")
    object InboxIncomingV11 : AppScreen("inbox_incoming_v11", "V11")

    // Inbox Screens for Outgoing
    object OutgoingV1 : AppScreen("outgoing_v1", "Outgoing V1")
    object OutgoingV2 : AppScreen("outgoing_v2", "Outgoing V2")
    object OutgoingV3 : AppScreen("outgoing_v3", "Outgoing V3")
    object OutgoingV4 : AppScreen("outgoing_v4", "Outgoing V4")
    object OutgoingV5 : AppScreen("outgoing_v5", "Outgoing V5")
    object OutgoingV6 : AppScreen("outgoing_v6", "Outgoing V6")
    object OutgoingV7 : AppScreen("outgoing_v7", "V7")

    object GroupSenderV1 : AppScreen("group_sender_v1", "Group Sender V1")
    object GroupSenderV2 : AppScreen("group_sender_v2", "Group Sender V2")
    object GroupSenderV3 : AppScreen("group_sender_v3", "Group Sender V3")
    object GroupSenderV4 : AppScreen("group_sender_v4", "Group Sender V4")

    companion object {

        // Grouped drawer structure
        val drawerStructure: List<DrawerSection>
            get() = listOf(

                DrawerSection(
                    title = "Send",
                    children = listOf(SendV1, SendV2, SendV3, SendV4)
                ),

                DrawerSection(
                    title = "Inbox",
                    children = listOf(InboxV1, InboxV2)
                ),

                DrawerSection(
                    title = "Incoming",
                    children = listOf(InboxIncomingV8,InboxIncomingV9,
                        InboxIncomingV10, InboxIncomingV11)
                ),
// outgoing section//
                DrawerSection(
                    title = "Outgoing",
                    children = listOf(
                        OutgoingV1, OutgoingV2, OutgoingV3, OutgoingV4, OutgoingV5,
                        OutgoingV6,OutgoingV7
                    )
                ),
                DrawerSection("Group Sender", listOf(GroupSenderV1, GroupSenderV2,
                    GroupSenderV3, GroupSenderV4))
            )
    }
}

data class DrawerSection(
    val title: String,
    val children: List<AppScreen>
)