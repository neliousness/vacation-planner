package com.pulsar.vacationplanner.presentation.onboarding

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.loc.newsapp.presentation.onboarding.components.OnBoardingPage
import com.pulsar.vacationplanner.presentation.onboarding.components.PagerIndicator
import com.pulsar.vacationplanner.presentation.common.components.NewsTextButton
import com.pulsar.vacationplanner.presentation.common.components.NextButton
import com.pulsar.vacationplanner.presentation.navgraph.Route
import com.pulsar.vacationplanner.ui.theme.Dimens.MediumPadding2
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingScreen(onEvent:(OnBoardingEvent) -> Unit, navHostController: NavHostController) {


    val viewModel : OnboardingViewModel = koinViewModel()


    //Observables
    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collectLatest { event -> 
            when(event)
            {
                is OnBoardingEvent.OnBoardingComplete -> {
                    navHostController.navigate(Route.HomeScreen.route)
                }
            }
        }

    }

    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val buttonsState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PagerIndicator(
                modifier = Modifier.width(52.dp),
                pagesSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()
                //Hide the button when the first element of the list is empty
                if (buttonsState.value[0].isNotEmpty()) {
                    NewsTextButton(
                        text = buttonsState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage - 1
                                )
                            }

                        }
                    )
                }
                NextButton(
                    text = buttonsState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 2) {
                                //TODO - go to main screen
                                Log.d("here", "fire")
                                onEvent(OnBoardingEvent.OnBoardingComplete)
                            } else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }

}
