package com.lashsolutions.firstcomposeapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lashsolutions.firstcomposeapp.ui.theme.FirstComposeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridTaskScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF000000),
                    titleContentColor = White
                ),
                title = { Text("Grid Task") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize().padding(innerPadding), color = White) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("This is Grid Task Screen", color = Black, fontSize = 24.sp)
            }
        }
    }
}



@Preview
@Composable
fun SettingsScreenPreview() {
    FirstComposeAppTheme {
        GridTaskScreen(onBackClick = {})
    }
}

//@Composable
//fun SettingsScreen(
//    modifier: Modifier = Modifier,
//    onBackClick: () -> Unit
//) {
//    Scaffold(
//        modifier = modifier.fillMaxSize(),
//        topBar = {
//            TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFF000000),
//                    titleContentColor = White
//                ),
//                title = { Text("Settings") },
//                navigationIcon = {
//                    IconButton(onClick = onBackClick) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->
//        Surface(
//            color = Color(0xFF121212),
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp),
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                // Add your settings content here
//                Text("App Settings", color = White, fontSize = 20.sp)
//                Text("Notification Preferences", color = White)
//                Text("Theme Options", color = White)
//                Text("About App", color = White)
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun SettingsScreenPreview() {
//    FirstComposeAppTheme {
//        SettingsScreen(onBackClick = {})
//    }
//}

//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun PaginatedGridScreen() {
//    val items = List(17) { "Item ${it + 1}" }
//
//    val itemsPerPage = 4
//    val pages = items.chunked(itemsPerPage) // Split into pages
//
//    val pagerState = rememberPagerState()
//
//    HorizontalPager(
//        pageCount = pages.size,
//        state = pagerState,
//        modifier = Modifier.fillMaxSize()
//    ) { pageIndex ->
//        val pageItems = pages[pageIndex]
//
//        // Display items in a 2x2 grid using LazyVerticalGrid
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            items(pageItems.size) { index ->
//                Box(
//                    modifier = Modifier
//                        .aspectRatio(1f)
//                        .background(Color.Cyan, RoundedCornerShape(12.dp))
//                        .fillMaxWidth(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(text = pageItems[index])
//                }
//            }
//        }
//    }
//}

//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import com.google.accompanist.pager.*
//import kotlin.math.ceil
//
//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun HorizontalPagedGrid() {
//    // Sample data - replace with your actual items
//    val items = (1..17).map { "Item $it" }
//
//    // Calculate number of pages needed (4 items per page)
//    val pageCount = ceil(items.size / 4.0).toInt()
//
//    HorizontalPager(
//        count = pageCount,
//        modifier = Modifier.fillMaxSize()
//    ) { pageIndex ->
//        // Calculate which items belong to this page
//        val startIndex = pageIndex * 4
//        val endIndex = minOf(startIndex + 4, items.size)
//        val pageItems = items.subList(startIndex, endIndex)
//
//        // Arrange items in 2x2 grid for this page
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Center
//        ) {
//            // First row of 2 items
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                for (i in 0..1) {
//                    if (i < pageItems.size) {
//                        GridItem(content = pageItems[i])
//                    } else {
//                        Spacer(modifier = Modifier.weight(1f)) // Empty space if less than 4 items
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Second row of 2 items
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                for (i in 2..3) {
//                    if (i < pageItems.size) {
//                        GridItem(content = pageItems[i])
//                    } else {
//                        Spacer(modifier = Modifier.weight(1f)) // Empty space if less than 4 items
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun GridItem(content: String) {
//    Box(
//        modifier = Modifier
//            .size(150.dp)
//            .background(Color.LightGray, RoundedCornerShape(8.dp)),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = content)
//    }
//}

//@Composable
//fun HorizontalPagedGridWithLazyRow() {
//    val items = (1..17).map { "Item $it" }
//    val groupedItems = items.chunked(4) // Split into groups of 4
//
//    LazyRow(
//        modifier = Modifier.fillMaxSize(),
//        contentPadding = PaddingValues(16.dp)
//    ) {
//        items(groupedItems.size) { pageIndex ->
//            Column(
//                modifier = Modifier
//                    .fillParentMaxWidth()
//                    .padding(horizontal = 8.dp),
//                verticalArrangement = Arrangement.Center
//            ) {
//                // First row
//                Row(
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    for (i in 0..1) {
//                        if (i < groupedItems[pageIndex].size) {
//                            GridItem(content = groupedItems[pageIndex][i])
//                        } else {
//                            Spacer(modifier = Modifier.weight(1f))
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Second row
//                Row(
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    for (i in 2..3) {
//                        if (i < groupedItems[pageIndex].size) {
//                            GridItem(content = groupedItems[pageIndex][i])
//                        } else {
//                            Spacer(modifier = Modifier.weight(1f))
//                        }
//                    }
//                }
//            }
//        }
//    }
//}