package com.example.composelayout1

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message){
    Row(modifier = Modifier.padding(all = 8.dp)){
        Image(
            painter = painterResource(id =R.drawable.profile_image),
            contentDescription = "profile pic",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant
                )
            Spacer(modifier = Modifier.width(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ){
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}




@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}


@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(
        msg = Message("Collegue", "I am Learning Jetpack Compose")
    )
}

fun getList(): List<Message> {
    var mutableList: List<Message> = mutableListOf(
        Message("Kshitij", "Did You know about jetpack compose"),
        Message("Kshitij", "Did You know about jetpack compose"),
        Message("Kshitij", "Did You know about jetpack compose"),
        Message("Kshitij", "Did You know about jetpack compose"),
        Message("Kshitij", "Did You know about jetpack compose"),
        Message("Kshitij", "Did You know about jetpack compose"),

        Message(
            "Rahul",
            "Yes it is an awesome way to build ui, I will learn and build application in jetpack compose...Yes it is an awesome way to build ui, I will learn and build application in jetpack compose.....Yes it is an awesome way to build ui, I will learn and build application in jetpack compose.....Yes it is an awesome way to build ui, I will learn and build application in jetpack compose....Yes it is an awesome way to build ui, I will learn and build application in jetpack compose"
        )
    )
    return mutableList
}
