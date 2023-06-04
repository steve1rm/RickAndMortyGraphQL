package me.androidbox.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import me.androidbox.rickandmorty.presentation.RickAndMortyViewModel
import me.androidbox.rickandmorty.presentation.screen.CharactersScreen
import me.androidbox.rickandmorty.ui.theme.RickAndMortyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RickAndMortyTheme {
                val rickAndMortyViewModel: RickAndMortyViewModel = hiltViewModel()
                val rickyAndMortyState by rickAndMortyViewModel.rickyAndMortyState.collectAsState()

                CharactersScreen(
                    rickAndMortyState = rickyAndMortyState,
                    modifier = Modifier.fillMaxSize(),
                    onSelectCharacter = { character ->
                        rickAndMortyViewModel.selectCharacter(character.id)
                    },
                    onDismissDialog = {
                        rickAndMortyViewModel.dismissDialog()
                    })
            }
        }
    }
}