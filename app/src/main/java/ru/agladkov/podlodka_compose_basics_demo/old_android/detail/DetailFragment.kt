package ru.agladkov.podlodka_compose_basics_demo.old_android.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.agladkov.podlodka_compose_basics_demo.R

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val items = viewModel.items.observeAsState(emptyList())

                Column(Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items.value.forEach { model ->
                            item {
                                when (model.itemType) {
                                    ItemType.Header -> HeaderCellView(model as HeaderDetailModel) {
                                        requireActivity().onBackPressed()
                                    }
                                    ItemType.Description -> DescriptionCellView(model as DescriptionDetailModel)
                                    ItemType.Image -> ImageCellView(model as ImageDetailModel)
                                    ItemType.Char -> CharCellView(model as CharDetailModel)
                                }
                            }
                        }
                    }

                    Card(
                        Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                        elevation = 8.dp
                    ) {
                        Row(
                            Modifier.fillMaxSize(),
                            verticalAlignment = CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(16.dp), text = "$9.99",
                                fontSize = 26.sp, fontWeight = FontWeight.Medium
                            )
                            Button(
                                modifier = Modifier.padding(end = 16.dp),
                                onClick = {
                                    Toast.makeText(requireContext(), "Buy", Toast.LENGTH_SHORT)
                                        .show()
                                }) {
                                Text(stringResource(id = R.string.action_buy))
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchDetailInfo("Title")
    }
}

@Composable
fun HeaderCellView(model: HeaderDetailModel, onCloseClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            text = model.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
        Text(modifier = Modifier
            .clickable {
                onCloseClick.invoke()
            }
            .padding(16.dp), text = stringResource(id = R.string.action_close))
    }
}

@Composable
fun DescriptionCellView(model: DescriptionDetailModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(model.description, color = Color.Black)
    }
}

@Composable
fun CharCellView(model: CharDetailModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = model.title,
            fontSize = 16.sp,
            color = Color.Black
        )
        Divider(color = Color.Black.copy(alpha = 0.2f), thickness = 0.5.dp)
    }
}

@Composable
fun ImageCellView(model: ImageDetailModel) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(250.dp),
        backgroundColor = Color(0xFF999999),
        shape = RoundedCornerShape(16.dp)
    ) {

    }
}