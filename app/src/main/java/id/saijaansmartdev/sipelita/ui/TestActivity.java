package id.saijaansmartdev.sipelita.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import id.saijaansmartdev.sipelita.R;
import id.saijaansmartdev.sipelita.helper.ClientHelper;
import id.saijaansmartdev.sipelita.helper.UserPreferences;
import id.saijaansmartdev.sipelita.model.AnswerTest;
import id.saijaansmartdev.sipelita.model.Question;
import id.saijaansmartdev.sipelita.model.Option;
import id.saijaansmartdev.sipelita.model.request.TestRequest;
import id.saijaansmartdev.sipelita.model.response.TestResponse;
import id.saijaansmartdev.sipelita.model.response.TestResultResponse;
import id.saijaansmartdev.sipelita.network.BapelkesPelitaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtNumberOfQuestion, txtSubTypeQuestion;
    private TextView txtIdQuestion, txtTypeQuestion, txtQuestion;
    private RadioGroup radioMultipleChoice, radioLevel;
    private RadioButton txtOptionA, txtOptionB, txtOptionC, txtOptionD;
    private RadioButton txtOption1, txtOption2, txtOption3, txtOption4, txtOption5;
    private EditText edtInlineAnswer;
    private Button btnBack, btnNext;
    private LinearLayout linearLayoutMultipleChoice, linearLayoutInlineText, linearLayoutLevel;

    private ArrayList<Question> QuestionsList = new ArrayList<Question>();
    private List<AnswerTest> test_lines;
    private ProgressDialog mLoading;
    private String type_test;
    private String UUID = "";
    private String TYPE = "";
    private Integer JumlahSoal = 0, PosisiSoal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        showLoading();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        init();

        listDataRequest();

    }

    private void init() {
        linearLayoutMultipleChoice  = (LinearLayout) findViewById(R.id.linear_multiple_choice);
        linearLayoutInlineText      = (LinearLayout) findViewById(R.id.linear_inline_text);
        linearLayoutLevel           = (LinearLayout) findViewById(R.id.linear_level_choice);

        txtTypeQuestion         = (TextView) findViewById(R.id.type_test);
        txtSubTypeQuestion      = (TextView) findViewById(R.id.sub_type_test);
        txtIdQuestion           = (TextView) findViewById(R.id.id_question_test);
        txtQuestion             = (TextView) findViewById(R.id.question_test);
        txtNumberOfQuestion     = (TextView) findViewById(R.id.number_of_questions);
        radioMultipleChoice     = (RadioGroup) findViewById(R.id.radio_group_option_answer);
        radioLevel              = (RadioGroup) findViewById(R.id.radio_group_level_answer);
        txtOptionA              = (RadioButton) findViewById(R.id.radio_option_a);
        txtOptionB              = (RadioButton) findViewById(R.id.radio_option_b);
        txtOptionC              = (RadioButton) findViewById(R.id.radio_option_c);
        txtOptionD              = (RadioButton) findViewById(R.id.radio_option_d);
        txtOption1              = (RadioButton) findViewById(R.id.radio_option_1);
        txtOption2              = (RadioButton) findViewById(R.id.radio_option_2);
        txtOption3              = (RadioButton) findViewById(R.id.radio_option_3);
        txtOption4              = (RadioButton) findViewById(R.id.radio_option_4);
        txtOption5              = (RadioButton) findViewById(R.id.radio_option_5);

        edtInlineAnswer         = (EditText) findViewById(R.id.edit_text_inline_text_detail);

        btnNext     = (Button) findViewById(R.id.btn_next_test);
        btnBack     = (Button) findViewById(R.id.btn_before_test);

        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        radioGroup();

    }

    private void radioGroup() {

        radioLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_option_1 :
                        try {
                            test_lines.set(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "1", "level"));
                        } catch (IndexOutOfBoundsException e) {
                            test_lines.add(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "1", "level"));
                        }
                        break;
                    case R.id.radio_option_2 :
                        try {
                            test_lines.set(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "2", "level"));
                        } catch (IndexOutOfBoundsException e) {
                            test_lines.add(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "2", "level"));
                        }
                        break;
                    case R.id.radio_option_3 :
                        try {
                            test_lines.set(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "3", "level"));
                        } catch (IndexOutOfBoundsException e) {
                            test_lines.add(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "3", "level"));
                        }
                        break;
                    case R.id.radio_option_4 :
                        try {
                            test_lines.set(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "4", "level"));
                        } catch (IndexOutOfBoundsException e) {
                            test_lines.add(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "4", "level"));
                        }
                        break;
                    case R.id.radio_option_5 :
                        try {
                            test_lines.set(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "5", "level"));
                        } catch (IndexOutOfBoundsException e) {
                            test_lines.add(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "5", "level"));
                        }
                        break;
                    default:
                        try {
                            test_lines.set(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "", "level"));
                        } catch (IndexOutOfBoundsException e) {
                            test_lines.add(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "", "level"));
                        }
                        break;

                }

            }
        });

        radioMultipleChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_option_a :
                        try {
                            test_lines.set(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "a", "multiplechoice"));
                        } catch (IndexOutOfBoundsException e) {
                            test_lines.add(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "a", "multiplechoice"));
                        }
                        break;
                    case R.id.radio_option_b :
                        try {
                            test_lines.set(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "b", "multiplechoice"));
                        } catch (IndexOutOfBoundsException e) {
                            test_lines.add(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "b", "multiplechoice"));
                        }
                        break;
                    case R.id.radio_option_c :
                        try {
                            test_lines.set(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "c", "multiplechoice"));
                        } catch (IndexOutOfBoundsException e) {
                            test_lines.add(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "c", "multiplechoice"));
                        }
                        break;
                    case R.id.radio_option_d :
                        try {
                            test_lines.set(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "d", "multiplechoice"));
                        } catch (IndexOutOfBoundsException e) {
                            test_lines.add(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "d", "multiplechoice"));
                        }
                        break;
                    default:
                        try {
                            test_lines.set(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "", "multiplechoice"));
                        } catch (IndexOutOfBoundsException e) {
                            test_lines.add(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), "", "multiplechoice"));
                        }
                        break;

                }
            }
        });
    }

    private void showLoading() {
        mLoading = new ProgressDialog(TestActivity.this);
        mLoading.setMessage("Mohon tunggu ....");
        mLoading.show();
    }

    private void listDataRequest() {

        if (getIntent().hasExtra("UUID"))
            UUID = getIntent().getStringExtra("UUID");

        if (getIntent().hasExtra("TYPE_TEST"))
            TYPE = getIntent().getStringExtra("TYPE_TEST");

        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<TestResponse> qTestResponseCall = api.questionTestEventResponseCall(UserPreferences.getKeyUserType(this)+" "+UserPreferences.getKeyUserToken(this), UUID, TYPE);

        qTestResponseCall.enqueue(new Callback<TestResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<TestResponse> call, @NotNull Response<TestResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("success")) {

                        txtTypeQuestion.setText(response.body().getData().getType_test().toUpperCase());
                        btnBack.setVisibility(View.INVISIBLE);

                        type_test       = response.body().getData().getType_test();
                        JumlahSoal      = response.body().getData().getJumlah_soal() != null ? response.body().getData().getJumlah_soal() : response.body().getData().getSoal().size();
                        QuestionsList   = response.body().getData().getSoal();

                        test_lines = new ArrayList<>(JumlahSoal);
                        setQuestion(response.body().getData().getSoal(), PosisiSoal);
                        setNumberOfQuestion(PosisiSoal + 1);

                        mLoading.dismiss();
                    } else {

                        String message = response.body() != null ? response.body().getMessage() : "Ada Sesuatu yang Salah, Silakan lapor ke Penyelenggara." ;

                        new AlertDialog.Builder(TestActivity.this)
                                .setMessage(message)
                                .setCancelable(false)
                                .setPositiveButton("Kembali", (dialog, id) -> {
                                  Intent intent = new Intent(TestActivity.this, DetailActivity.class);
                                  intent.putExtra("PELATIHAN", UUID);
                                  startActivity(intent);
                                  finish();
                                })
                                .show();

                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<TestResponse> call, @NotNull Throwable t) {
                mLoading.dismiss();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setNumberOfQuestion(int number)
    {
        String text_number_question = number + "/" + JumlahSoal.toString();
        txtNumberOfQuestion.setText(text_number_question);

        if ( JumlahSoal.toString().equals("1") ) {
           btnNext.setText("Kirim");
        }
    }

    @SuppressLint("SetTextI18n")
    private void setQuestion(ArrayList<Question> questions, int positionSoal)
    {
        Question question = questions.get(positionSoal);

        if (type_test.equals("evaluation"))
        {
            txtSubTypeQuestion.setVisibility(View.VISIBLE);
            txtSubTypeQuestion.setText(question.getEvaluation_name());
        }
        else
        {
            txtSubTypeQuestion.setVisibility(View.GONE);
            txtSubTypeQuestion.setText("");
        }

        txtIdQuestion.setText(question.getId());
        txtQuestion.setText(question.getQuestion());

        switch (question.getType()) {
            case "multiplechoice" :
                linearLayoutMultipleChoice.setVisibility(View.VISIBLE);
                linearLayoutInlineText.setVisibility(View.GONE);
                linearLayoutLevel.setVisibility(View.GONE);

                setOptionsAnswer(question.getOptions(), question.getType());

                try {

                    switch (test_lines.get(positionSoal).getAnswer()) {
                        case "a" :
                            txtOptionA.setChecked(true);
                            break;
                        case "b" :
                            txtOptionB.setChecked(true);
                            break;
                        case "c" :
                            txtOptionC.setChecked(true);
                            break;
                        case "d" :
                            txtOptionD.setChecked(true);
                            break;
                        default:
                            edtInlineAnswer.setText(test_lines.get(positionSoal).getAnswer());
                            break;
                    }

                } catch (IndexOutOfBoundsException ignored) {

                }

                break;
            case "question" :
                linearLayoutMultipleChoice.setVisibility(View.GONE);
                linearLayoutInlineText.setVisibility(View.VISIBLE);
                linearLayoutLevel.setVisibility(View.GONE);

                edtInlineAnswer.setText("");
                break;
            case "level" :
                linearLayoutMultipleChoice.setVisibility(View.GONE);
                linearLayoutInlineText.setVisibility(View.GONE);
                linearLayoutLevel.setVisibility(View.VISIBLE);

                radioLevel.clearCheck();
                break;
        }

    }

    @SuppressLint("SetTextI18n")
    private void setOptionsAnswer(ArrayList<Option> options, String type_test)
    {

        switch (type_test) {
            case "multiplechoice" :

                radioMultipleChoice.clearCheck();

                txtOptionA.setText(options.get(0).getOption_key() + ". " + options.get(0).getOption_text());
                txtOptionB.setText(options.get(1).getOption_key() + ". " + options.get(1).getOption_text());
                txtOptionC.setText(options.get(2).getOption_key() + ". " + options.get(2).getOption_text());
                txtOptionD.setText(options.get(3).getOption_key() + ". " + options.get(3).getOption_text());

                break;

            case "question":
                edtInlineAnswer.setText("");
                break;

            case "level" :
                radioLevel.clearCheck();
                break;
        }



    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        if (QuestionsList.get(PosisiSoal).getType().equals("question"))
        {
            try {
                test_lines.set(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), edtInlineAnswer.getText().toString(), "question"));
            } catch (IndexOutOfBoundsException e) {
                test_lines.add(PosisiSoal, new AnswerTest(txtIdQuestion.getText().toString(), edtInlineAnswer.getText().toString(), "question"));
            }
        }

        switch (v.getId())
        {
            case R.id.btn_next_test :

                if (btnNext.getText().equals("Kirim"))
                {
                    new AlertDialog.Builder(this)
                        .setMessage("Apakah anda yakin ingin mengirim jawaban?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", (dialog, id) -> sendAnswer())
                        .setNegativeButton("Tidak", null)
                        .show();
                }
                else
                {
                    buttonAction("next");
                }
                break;
            case R.id.btn_before_test :
                buttonAction("back");
                break;
            default:
                break;
        }
    }

    private void sendAnswer()
    {
        for (int i = 0; i < test_lines.size(); i++)
        {
            Log.d("_SEND_ANSWER_", i + " " + test_lines.get(i).getId() + " " + test_lines.get(i).getAnswer());
        }

        TestRequest data = new TestRequest(UUID, JumlahSoal.toString(),test_lines);

        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<TestResultResponse> qTestResponseCall = api.questionResultResponzeCall(UserPreferences.getKeyUserType(this)+" "+UserPreferences.getKeyUserToken(this), TYPE, data);

        qTestResponseCall.enqueue(new Callback<TestResultResponse>() {
            @Override
            public void onResponse(Call<TestResultResponse> call, Response<TestResultResponse> response) {
                Intent intent = new Intent(TestActivity.this, DetailActivity.class);
                intent.putExtra("PELATIHAN", UUID);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<TestResultResponse> call, Throwable t) {
                Intent intent = new Intent(TestActivity.this, DetailActivity.class);
                intent.putExtra("PELATIHAN", UUID);
                startActivity(intent);
                finish();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void buttonAction(String action)
    {

        if (action.equals("next"))
        {
            setQuestion(QuestionsList, ++PosisiSoal);

            setNumberOfQuestion(PosisiSoal + 1);

            if ((JumlahSoal - 1) <= PosisiSoal) {
                btnNext.setText("Kirim");
            }

            if (PosisiSoal > 0) {
                btnBack.setVisibility(View.VISIBLE);
            }
        }
        else if (action.equals("back"))
        {
            setQuestion(QuestionsList, --PosisiSoal);

            setNumberOfQuestion(PosisiSoal + 1);

            if (PosisiSoal <= 0) {
                btnBack.setVisibility(View.INVISIBLE);
            }

            if ((JumlahSoal - 1) > PosisiSoal) {
                btnNext.setText("Selanjutnya");
            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TestActivity.this, DetailActivity.class);
        intent.putExtra("PELATIHAN", UUID);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}