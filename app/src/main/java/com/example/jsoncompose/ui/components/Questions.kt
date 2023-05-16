package com.example.jsoncompose.ui.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jsoncompose.common.AppColors
import com.example.jsoncompose.data.model.JsonResponseItem
import com.example.jsoncompose.ui.viewmodel.QuestionsViewModel

/**
 * @author : Mingaleev D
 * @data : 14.05.2023
 */

@Composable
fun Questions(viewModel: QuestionsViewModel) {
   val questions = viewModel.data.value.data?.toMutableList()
   if (viewModel.data.value.loading == true) {
      CircularProgressIndicator()
   } else {
      if(questions !=null){
         QuestionDisplay(question = questions.first())
      }
//      questions?.forEach { item ->
//
//      }
   }
}

//@Preview
@Composable
fun QuestionDisplay(
    question: JsonResponseItem,
    //questionIndex: MutableSet<Int>,
   // viewModel: QuestionsViewModel,
    onNextClicked: (Int) -> Unit = {}
) {
   val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
   val choicesState = remember(question) {
      question.choices.toMutableList()
   }
   val answerState = remember(question) {
      mutableStateOf<Int?>(null)
   }
   val correctAnswerState = remember(question) {
      mutableStateOf<Boolean?>(null)
   }
   val updateAnswer: (Int) -> Unit = remember(question) {
      {
         answerState.value = it
         correctAnswerState.value = choicesState[it] == question.answer
      }
   }
   Surface(modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .padding(4.dp),
           color = AppColors.mDarkPurple
   ) {
      Column(modifier = Modifier.padding(12.dp),
             verticalArrangement = Arrangement.Top,
             horizontalAlignment = Alignment.Start
      ) {
         QuestionsTracker()
         DrawDottedLine(pathEffect = pathEffect)

         Column {
            Text(modifier = Modifier
               .padding(6.dp)
               .align(alignment = Alignment.Start)
               .fillMaxHeight(0.3f),
                 text = question.question,
                 fontSize = 18.sp,
                 fontWeight = FontWeight.Bold,
                 lineHeight = 22.sp,
                 color = AppColors.mOffWhite
            )
            //choices
            choicesState.forEachIndexed { index, s ->
               Row(modifier = Modifier
                  .padding(4.dp)
                  .fillMaxWidth()
                  .height(44.dp)
                  .border(width = 4.dp,
                          brush = Brush.linearGradient(colors = listOf(AppColors.mDarkPurple,
                                                                       AppColors.mDarkPurple)),
                          shape = RoundedCornerShape(16.dp)
                  )
                  .clip(RoundedCornerShape(topStartPercent = 50,
                                           topEndPercent = 50,
                                           bottomStartPercent = 50,
                                           bottomEndPercent = 50))
                  .background(Color.Transparent),
                   verticalAlignment = Alignment.CenterVertically
               ) {
                  RadioButton(
                      selected = (answerState.value == index),
                      onClick = {
                         updateAnswer(index)
                      },
                      modifier = Modifier.padding(start = 16.dp),
                      colors = RadioButtonDefaults.colors(selectedColor =
                                                          if (correctAnswerState.value == true && index == answerState.value) {
                                                             Color.Green.copy(alpha = 0.2f)
                                                          } else {
                                                             Color.Red.copy(alpha = 0.2f)
                                                          }))
                  Text(text = "Text")


               }
            }
         }
      }
   }
}

@Composable
fun DrawDottedLine(pathEffect: androidx.compose.ui.graphics.PathEffect) {
   Canvas(modifier = Modifier
      .fillMaxWidth()
      .height(2.dp),
          onDraw = {
             drawLine(color = AppColors.mLightGray,
                      start = Offset(0f, 0f),
                      end = Offset(size.width, y = 0f),
                      pathEffect = pathEffect
             )
          })
}

@Preview
@Composable
fun QuestionsTracker(
    counter: Int = 10,
    outOf: Int = 100
) {
   Text(text = buildAnnotatedString {
      withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
         withStyle(style = SpanStyle(color = AppColors.mLightGray,
                                     fontWeight = FontWeight.Bold,
                                     fontSize = 27.sp
         )
         ) {
            append("Question $counter/")
            withStyle(style = SpanStyle(color = AppColors.mLightGray,
                                        fontWeight = FontWeight.Light,
                                        fontSize = 14.sp
            )
            ) {
               append("$outOf")
            }

         }
      }
   },
        modifier = Modifier.padding(20.dp)
   )

}
