package me.androidbox.rickandmorty.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import me.androidbox.rickandmorty.R
import me.androidbox.rickandmorty.domain.model.CharacterModel
import me.androidbox.rickandmorty.domain.model.CharactersModel
import me.androidbox.rickandmorty.presentation.RickyAndMortyState
import me.androidbox.rickandmorty.ui.theme.RickAndMortyTheme
import java.util.UUID

@Composable
fun CharactersScreen(
    rickAndMortyState: RickyAndMortyState,
    modifier: Modifier = Modifier,
    onSelectCharacter: (character: CharactersModel) -> Unit,
    onDismissDialog: () -> Unit
) {
    Box(modifier = modifier) {
        if(rickAndMortyState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(rickAndMortyState.listOfCharacter) { character ->
                    CharacterItem(
                        character = character,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelectCharacter(character)
                            })
                }
            }

            if(rickAndMortyState.character != null) {
                CharacterDialog(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color.White)
                        .padding(16.dp),
                    characterModel = rickAndMortyState.character,
                    onDismiss = {
                        onDismissDialog()
                    })
            }
        }
    }
}

@Composable
fun CharacterDialog(
    characterModel: CharacterModel,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier) {

    val episodes = remember(characterModel.episode) {
        characterModel.episode.take(3).joinToString()
    }

    Dialog(
        onDismissRequest = onDismiss) {

        Column(modifier = modifier) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    modifier = Modifier.clip(CircleShape),
                    model = characterModel.image,
                    contentDescription = "Image of morty"
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = characterModel.name,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Gender: ${characterModel.gender}", color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "Status: ${characterModel.status}", color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "Location: ${characterModel.location?.name.orEmpty()}", color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "Episodes: $episodes", color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun CharacterItem(
    character: CharactersModel,
    modifier: Modifier) {
    Row(modifier = modifier,
        verticalAlignment = Alignment.CenterVertically) {

        AsyncImage(
            model = character.image,
            contentScale = ContentScale.Crop,
            contentDescription = "Image of morty"
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = character.name, color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = character.species, color = Color.DarkGray, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCharactersScreen() {
    RickAndMortyTheme {
        CharactersScreen(
            rickAndMortyState = RickyAndMortyState(isLoading = true) ,
            modifier = Modifier.fillMaxSize(),
            onDismissDialog = {},
            onSelectCharacter = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCharacterItem() {
    RickAndMortyTheme {
        CharacterItem(
            modifier = Modifier,
            character = CharactersModel(
            id = UUID.randomUUID().toString(),
            image = UUID.randomUUID().toString(),
            name = "Steve Mason",
            species = "Alien Species"
        ))
    }
}
