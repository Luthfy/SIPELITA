package id.digilabyte.sipelita.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.PickerManager;
import id.digilabyte.sipelita.R;
import id.digilabyte.sipelita.helper.ClientHelper;
import id.digilabyte.sipelita.helper.UserPreferences;
import id.digilabyte.sipelita.model.Cities;
import id.digilabyte.sipelita.model.Districts;
import id.digilabyte.sipelita.model.Educations;
import id.digilabyte.sipelita.model.FormPelatihan;
import id.digilabyte.sipelita.model.Groups;
import id.digilabyte.sipelita.model.Positions;
import id.digilabyte.sipelita.model.Provincies;
import id.digilabyte.sipelita.model.Villages;
import id.digilabyte.sipelita.model.request.FormRequest;
import id.digilabyte.sipelita.model.response.CitiesResponse;
import id.digilabyte.sipelita.model.response.DistrictsResponse;
import id.digilabyte.sipelita.model.response.EducationResponse;
import id.digilabyte.sipelita.model.response.FormResponse;
import id.digilabyte.sipelita.model.response.GroupResponse;
import id.digilabyte.sipelita.model.response.PositionResponse;
import id.digilabyte.sipelita.model.response.ProvinciesResponse;
import id.digilabyte.sipelita.model.response.UploadFileResponse;
import id.digilabyte.sipelita.model.response.VillagesResponse;
import id.digilabyte.sipelita.network.BapelkesPelitaApi;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPelatihanActivity extends AppCompatActivity {

    private static final int CAMERA_CODE_REQUEST = 101;
    private static final int FILE_CODE_REQUEST = 201;
    private static final int RC_CAMERA_AND_STORAGE = 301;
    TextInputEditText edtNipForm, edtNamaForm, edtTempatLahir, edtTanggalLahir, edtNoHp, edtAlamat;
    TextInputEditText edtCompanyName, edtCompanyAddress, edtWorkUnit, edtUnitWork, edtMajor, edtGraduate, edtNamaJabatan;
    SearchableSpinner spinnerProv, spinnerCity, spinnerDistrict, spinnerVillage, spinnerKelompok;
    Spinner spinnerGroup, spinnerEducation, spinnerPosition;
    RadioGroup  radioGroupJenkel;
    RadioButton radioLaki, radioPerempuan;
    private Button btnRegistrasiPeserta, btnKtp, btnDocument;

    private ProgressDialog mLoading;

    private String Auth = "", UUID = "";
    private String spinnerProvValue = "", spinnerCityValue = "", spinnerDistrictValue = "", spinnerVillageValue = "";
    private String spinnerGroupValue = "", spinnerEducationValue = "", spinnerPositionValue = "", spinnerKelompokValue = "";

    private ArrayList<Provincies.Provincy> provinciesArrayList   = new ArrayList<>();
    private ArrayList<Cities.City> citiesArrayList           = new ArrayList<>();
    private ArrayList<Districts.District> districtsArrayList     = new ArrayList<>();
    private ArrayList<Villages.Village> villagesArrayList       = new ArrayList<>();
    private ArrayList<Groups.Group> groupArrayList             = new ArrayList<>();
    private ArrayList<Positions.Position> positionArrayList       = new ArrayList<>();
    private ArrayList<Educations.Education> educationArrayList     = new ArrayList<>();

    private ArrayList<String> photos = new ArrayList<>();
    private ArrayList<String> documents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pelatihan);

        methodRequiresTwoPermission();

        showLoading();

        PickerManager.INSTANCE.reset();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_toolbar_transparent));

        initComp();

        Auth = UserPreferences.getKeyUserToken(this);

        if (Auth.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Anda Harus Login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(FormPelatihanActivity.this, LoginActivity.class));
            finish();
        }

        UUID = getIntent().getStringExtra("UUID");

        if (UUID.isEmpty()) {
            Toast.makeText(getApplicationContext(), "UUID Tidak Valid", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }

        btnRegistrasiPeserta = (Button) findViewById(R.id.btn_daftar_form_pelatihan);
        btnRegistrasiPeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(FormPelatihanActivity.this)
                    .setMessage("Apakah data telah sesuai?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", (dialog, id) -> {

                        String jenkel = "";
                        int selectedAddId = radioGroupJenkel.getCheckedRadioButtonId();

                        switch (selectedAddId) {
                            case R.id.radio_l : jenkel = "l"; break;
                            case R.id.radio_p : jenkel = "p"; break;
                        }

                        FormRequest formRequest = new FormRequest();

                        if (edtNipForm.getText().toString().isEmpty()) {
                            edtNipForm.setError("Tidak Boleh Kosong");
                            edtNipForm.requestFocus();
                        } else if (edtNamaForm.getText().toString().isEmpty()) {
                            edtNamaForm.setError("Tidak Boleh Kosong");
                            edtNamaForm.requestFocus();
                        } else if (edtTempatLahir.getText().toString().isEmpty()) {
                            edtTempatLahir.setError("Tidak Boleh Kosong");
                            edtTempatLahir.requestFocus();
                        } else if (edtTanggalLahir.getText().toString().isEmpty()) {
                            edtTanggalLahir.setError("Tidak Boleh Kosong");
                            edtTanggalLahir.requestFocus();
                        } else if (edtNoHp.getText().toString().isEmpty()) {
                            edtNoHp.setError("Tidak Boleh Kosong");
                            edtNoHp.requestFocus();
                        } else if (edtAlamat.getText().toString().isEmpty()) {
                            edtAlamat.setError("Tidak Boleh Kosong");
                            edtAlamat.requestFocus();
                        } else if (jenkel.isEmpty()) {
                            Toast.makeText(FormPelatihanActivity.this, "Jenis Kelamin Belum Di Pilih", Toast.LENGTH_SHORT).show();
                            radioGroupJenkel.requestFocus();
                        } else if (edtCompanyName.getText().toString().isEmpty()) {
                            edtCompanyName.setError("Tidak Boleh Kosong");
                            edtCompanyName.requestFocus();
                        } else if (edtCompanyAddress.getText().toString().isEmpty()) {
                            edtCompanyAddress.setError("Tidak Boleh Kosong");
                            edtCompanyAddress.requestFocus();
                        } else if (spinnerProvValue.isEmpty()) {
                            Toast.makeText(FormPelatihanActivity.this, "Silakan Pilih Provinsi terlebih dahulu", Toast.LENGTH_SHORT).show();
                            spinnerProv.requestFocus();
                        } else if (spinnerCityValue.isEmpty()) {
                            Toast.makeText(FormPelatihanActivity.this, "Silakan Pilih Kota/Kab terlebih dahulu", Toast.LENGTH_SHORT).show();
                            spinnerCity.requestFocus();
                        } else if (spinnerDistrictValue.isEmpty()) {
                            Toast.makeText(FormPelatihanActivity.this, "Silakan Pilih Kecamatan terlebih dahulu", Toast.LENGTH_SHORT).show();
                            spinnerDistrict.requestFocus();
                        } else if (spinnerVillageValue.isEmpty()) {
                            Toast.makeText(FormPelatihanActivity.this, "Silakan Pilih Desa/Kelurahan terlebih dahulu", Toast.LENGTH_SHORT).show();
                            spinnerVillage.requestFocus();
                        } else if (edtWorkUnit.getText().toString().isEmpty()) {
                            edtWorkUnit.setError("Tidak Boleh Kosong");
                            edtWorkUnit.requestFocus();
                        } else if (edtUnitWork.getText().toString().isEmpty()) {
                            edtUnitWork.setError("Tidak Boleh Kosong");
                            edtUnitWork.requestFocus();
                        } else if (spinnerKelompokValue.isEmpty()) {
                            Toast.makeText(FormPelatihanActivity.this, "Silakan Pilih Kategori Pekerjaan terlebih dahulu", Toast.LENGTH_SHORT).show();
                            spinnerKelompok.requestFocus();
                        } else if (edtNamaJabatan.getText().toString().isEmpty()) {
                            edtNamaJabatan.setError("Tidak Boleh Kosong");
                            edtNamaJabatan.requestFocus();
                        } else if (spinnerGroupValue.isEmpty()) {
                            Toast.makeText(FormPelatihanActivity.this, "Silakan Pilih Group terlebih dahulu", Toast.LENGTH_SHORT).show();
                            spinnerGroup.requestFocus();
                        } else if (spinnerPositionValue.isEmpty()) {
                            Toast.makeText(FormPelatihanActivity.this, "Silakan Pilih Posisi terlebih dahulu", Toast.LENGTH_SHORT).show();
                            spinnerPosition.requestFocus();
                        } else if (spinnerEducationValue.isEmpty()) {
                            Toast.makeText(FormPelatihanActivity.this, "Silakan Pilih Jenjang Pendidikan terlebih dahulu", Toast.LENGTH_SHORT).show();
                            spinnerEducation.requestFocus();
                        } else if (edtMajor.getText().toString().isEmpty()) {
                            edtMajor.setError("Tidak Boleh Kosong");
                            edtMajor.requestFocus();
                        } else if (edtGraduate.getText().toString().isEmpty()) {
                            edtGraduate.setError("Tidak Boleh Kosong");
                            edtGraduate.requestFocus();
                        } else {
                            showLoading();
                            formRequest.setNip(edtNipForm.getText().toString());
                            formRequest.setName(edtNamaForm.getText().toString());
                            formRequest.setBirthplace(edtTempatLahir.getText().toString());
                            formRequest.setBirthday(edtTanggalLahir.getText().toString());
                            formRequest.setPhone(edtNoHp.getText().toString());
                            formRequest.setAddress(edtAlamat.getText().toString());
                            formRequest.setJenkel(jenkel);
                            formRequest.setAgency(edtCompanyName.getText().toString());
                            formRequest.setAgency_address(edtCompanyAddress.getText().toString());
                            formRequest.setAgency_province(Integer.valueOf(spinnerProvValue));
                            formRequest.setAgency_city(Integer.valueOf(spinnerCityValue));
                            formRequest.setAgency_district(Integer.valueOf(spinnerDistrictValue));
                            formRequest.setAgency_village(spinnerVillageValue);
                            formRequest.setWork_of_unit(edtWorkUnit.getText().toString());
                            formRequest.setUnit_of_work(edtUnitWork.getText().toString());
                            formRequest.setWork_category(spinnerKelompokValue.toLowerCase());
                            formRequest.setWork_position_name(edtNamaJabatan.getText().toString());
                            formRequest.setGroup_id(spinnerGroupValue);
                            formRequest.setPosition_id(spinnerPositionValue);
                            formRequest.setEducation_id(spinnerEducationValue);
                            formRequest.setMajors(edtMajor.getText().toString());
                            formRequest.setGraduation(edtGraduate.getText().toString());

                            if ( photos.size() > 0 || documents.size() > 0) {
                                Log.d("_form_proses", "upload_file");
                                registeredRequest(formRequest);
                            } else {
                                Log.d("_form_proses", "tidak upload");
                                registeredRequest(formRequest);
                            }

                            Toast.makeText(FormPelatihanActivity.this, "Sudah terisi", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Tidak", null)
                    .show();

            }
        });
    }

    private void uploadFile() {

        ArrayList<File> files = new ArrayList<>();
        ArrayList<RequestBody> filesBody = new ArrayList<>();
        ArrayList<MultipartBody.Part> partsFile = new ArrayList<>();

        for (int i = 0;i < documents.size();i++) {
            files.add(new File(documents.get(i)));
            filesBody.add(RequestBody.create(MediaType.parse("multipart/form-data"), files.get(i)));
            partsFile.add(MultipartBody.Part.createFormData("file[]", files.get(i).getName(), filesBody.get(i)));
        }

        ArrayList<File> image = new ArrayList<>();
        ArrayList<RequestBody> photosBody = new ArrayList<>();
        ArrayList<MultipartBody.Part> partsPhoto = new ArrayList<>();

        for (int i = 0;i < photos.size();i++) {
            image.add(new File(photos.get(i)));
            photosBody.add(RequestBody.create(MediaType.parse("multipart/form-data"), image.get(i)));
            partsPhoto.add(MultipartBody.Part.createFormData("photo[]", image.get(i).getName(), photosBody.get(i)));
        }


        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<UploadFileResponse> uploadFile = api.uploadFilePelatihan(UserPreferences.getKeyUserType(this)+" "+UserPreferences.getKeyUserToken(this), this.UUID, partsPhoto, partsFile);

        uploadFile.enqueue(new Callback<UploadFileResponse>() {
            @Override
            public void onResponse(Call<UploadFileResponse> call, Response<UploadFileResponse> response) {
                Toast.makeText(FormPelatihanActivity.this, "Pelatihan berhasil di upload", Toast.LENGTH_SHORT).show();
                mLoading.dismiss();
            }

            @Override
            public void onFailure(Call<UploadFileResponse> call, Throwable t) {
                Toast.makeText(FormPelatihanActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                mLoading.dismiss();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        PickerManager.INSTANCE.reset();
    }

    private void showLoading() {
        mLoading = new ProgressDialog(FormPelatihanActivity.this);
        mLoading.setMessage("Mohon tunggu ....");
        mLoading.show();
    }

    private void registeredRequest(FormRequest fr) {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<FormResponse> formRegisterPelatihanResponseCall = api.formRegisterPelatihanResponseCall(UserPreferences.getKeyUserType(this)+" "+Auth, fr, UUID);

        formRegisterPelatihanResponseCall.enqueue(new Callback<FormResponse>() {
            @Override
            public void onResponse(Call<FormResponse> call, Response<FormResponse> response) {
                if (response.isSuccessful()) {
                    mLoading.dismiss();
                    uploadFile();
                    assert response.body() != null;
                    FormPelatihan.Participant fm = response.body().getData().getParticipant();
                    Intent intent = new Intent(FormPelatihanActivity.this, CheckOutActivity.class);
                    intent.putExtra("FORM", fm);
                    startActivity(intent);
                } else {
                    FormResponse err = response.body();
                    Toast.makeText(getApplicationContext(), "Tidak Dapat Mendaftar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FormResponse> call, Throwable t) {
            Toast.makeText(FormPelatihanActivity.this, t.getMessage(),Toast.LENGTH_SHORT).show();
                mLoading.dismiss();
            }
        });
    }

    private void initComp() {
        edtNipForm      = (TextInputEditText) findViewById(R.id.edt_nip_form_pelatihan);
        edtNamaForm     = (TextInputEditText) findViewById(R.id.edt_nama_form_pelatihan);
        edtTempatLahir  = (TextInputEditText) findViewById(R.id.edt_tempat_lahir_form_pelatihan);
        edtTanggalLahir = (TextInputEditText) findViewById(R.id.edt_tgl_lahir_form_pelatihan);
        edtNoHp         = (TextInputEditText) findViewById(R.id.edt_nohp_form_pelatihan);
        edtAlamat       = (TextInputEditText) findViewById(R.id.edt_alamat_form_pelatihan);
        edtCompanyName  = (TextInputEditText) findViewById(R.id.edt_company_name_form_pelatihan);
        edtCompanyAddress = (TextInputEditText) findViewById(R.id.edt_company_address_form_pelatihan);
        edtWorkUnit     = (TextInputEditText) findViewById(R.id.edt_work_unit_form_pelatihan);
        edtUnitWork     = (TextInputEditText) findViewById(R.id.edt_unit_work_form_pelatihan);
        edtNamaJabatan  = (TextInputEditText) findViewById(R.id.edt_nama_jabatan_form_pelatihan);
        edtMajor        = (TextInputEditText) findViewById(R.id.edt_major_form_pelatihan);
        edtGraduate     = (TextInputEditText) findViewById(R.id.edt_kelulusan_form_pelatihan);
        spinnerProv     = (SearchableSpinner) findViewById(R.id.spinner_prov_form_pelatihan);
        spinnerCity     = (SearchableSpinner) findViewById(R.id.spinner_city_form_pelatihan);
        spinnerDistrict = (SearchableSpinner) findViewById(R.id.spinner_district_form_pelatihan);
        spinnerVillage  = (SearchableSpinner) findViewById(R.id.spinner_village_form_pelatihan);
        spinnerKelompok = (SearchableSpinner) findViewById(R.id.spinner_kategori_form_pelatihan);
        spinnerGroup    = (SearchableSpinner) findViewById(R.id.spinner_group_form_pelatihan);
        spinnerEducation= (SearchableSpinner) findViewById(R.id.spinner_education_form_pelatihan);
        spinnerPosition = (SearchableSpinner) findViewById(R.id.spinner_position_form_pelatihan);
        radioGroupJenkel= (RadioGroup) findViewById(R.id.radio_group_jenkel);
        radioLaki       = (RadioButton) findViewById(R.id.radio_l);
        radioPerempuan  = (RadioButton) findViewById(R.id.radio_p);
        btnKtp = (Button) findViewById(R.id.btn_ktp_form_pelatihan);
        btnDocument = (Button) findViewById(R.id.btn_surat_tugas_form_pelatihan);

        btnKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilePickerBuilder.getInstance()
                        .pickPhoto(FormPelatihanActivity.this, CAMERA_CODE_REQUEST);
            }
        });

        btnDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilePickerBuilder.getInstance()
                        .pickFile(FormPelatihanActivity.this, FILE_CODE_REQUEST);
            }
        });

        edtTanggalLahir .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear   = c.get(Calendar.YEAR);
                int mMonth  = c.get(Calendar.MONTH);
                int mDay    = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(FormPelatihanActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edtTanggalLahir.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        getProvincy();
        getGroup();
        getPosition();
        getEducation();
        setKelompokSearchableSpinner();
    }

    private void setProvSearchableSpinner(ArrayList<Provincies.Provincy> prov) {
        ArrayList<String> dataListArray = new ArrayList<>();
        for (Provincies.Provincy data : prov)
        {
            dataListArray.add(data.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(FormPelatihanActivity.this, android.R.layout.simple_list_item_1, dataListArray);
        spinnerProv.setAdapter(adapter);

        spinnerProv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerProvValue = prov.get(position).getId();
                getCity(spinnerProvValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setCitySearchableSpinner(ArrayList<Cities.City> city) {
        ArrayList<String> dataListArray = new ArrayList<>();
        for (Cities.City data : city)
        {
            dataListArray.add(data.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(FormPelatihanActivity.this, android.R.layout.simple_list_item_1, dataListArray);
        spinnerCity.setAdapter(adapter);

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerCityValue = city.get(position).getId();
                getDistrict(spinnerCityValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setDistrictSearchableSpinner(ArrayList<Districts.District> dist) {
        ArrayList<String> dataListArray = new ArrayList<>();

        for (Districts.District data : dist)
        {
            dataListArray.add(data.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(FormPelatihanActivity.this, android.R.layout.simple_list_item_1, dataListArray);
        spinnerDistrict.setAdapter(adapter);

        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerDistrictValue = dist.get(position).getId();
                getVillage(spinnerDistrictValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setVillageSearchableSpinner(ArrayList<Villages.Village> village) {
        mLoading.dismiss();
        ArrayList<String> dataListArray = new ArrayList<>();
        for (Villages.Village data : village)
        {
            dataListArray.add(data.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(FormPelatihanActivity.this, android.R.layout.simple_list_item_1, dataListArray);
        spinnerVillage.setAdapter(adapter);

        spinnerVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerVillageValue = village.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setKelompokSearchableSpinner() {
        ArrayList<String> dataListArray = new ArrayList<>();
        dataListArray.add("Struktural");
        dataListArray.add("Fungsional");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(FormPelatihanActivity.this, android.R.layout.simple_list_item_1, dataListArray);
        spinnerKelompok.setAdapter(adapter);

        spinnerKelompok.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerKelompokValue = dataListArray.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setGroupSearchableSpinner(ArrayList<Groups.Group> groups) {
        ArrayList<String> dataListArray = new ArrayList<>();
        for (Groups.Group group : groups) {
            dataListArray.add(group.getCode()+" "+group.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(FormPelatihanActivity.this, android.R.layout.simple_list_item_1, dataListArray);
        spinnerGroup.setAdapter(adapter);

        spinnerGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerGroupValue = groups.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setPositionSearchableSpinner(ArrayList<Positions.Position> pos) {
        ArrayList<String> dataListArray = new ArrayList<>();
        for (Positions.Position position : pos) {
            dataListArray.add(position.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(FormPelatihanActivity.this, android.R.layout.simple_list_item_1, dataListArray);
        spinnerPosition.setAdapter(adapter);

        spinnerPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPositionValue = pos.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setEducationSearchableSpinner(ArrayList<Educations.Education> edu) {
        ArrayList<String> dataListArray = new ArrayList<>();
        for (Educations.Education education : edu) {
            dataListArray.add(education.getCode());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(FormPelatihanActivity.this, android.R.layout.simple_list_item_1, dataListArray);
        spinnerEducation.setAdapter(adapter);

        spinnerEducation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerEducationValue = edu.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getProvincy() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<ProvinciesResponse> provinciesResponseCall = api.provinciesResponseCall();

        provinciesResponseCall.enqueue(new Callback<ProvinciesResponse>() {
            @Override
            public void onResponse(Call<ProvinciesResponse> call, Response<ProvinciesResponse> response) {
                provinciesArrayList = Objects.requireNonNull(response.body()).getData().getProvinces();
                setProvSearchableSpinner(provinciesArrayList);
            }

            @Override
            public void onFailure(Call<ProvinciesResponse> call, Throwable t) {

            }
        });
    }

    private void getCity(String id) {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<CitiesResponse> citiesResponseCall = api.citiesResponseCall(id);

        citiesResponseCall.enqueue(new Callback<CitiesResponse>() {
            @Override
            public void onResponse(Call<CitiesResponse> call, Response<CitiesResponse> response) {
                citiesArrayList = response.body().getData().getCities();
                setCitySearchableSpinner(citiesArrayList);
            }

            @Override
            public void onFailure(Call<CitiesResponse> call, Throwable t) {

            }
        });
    }

    private void getDistrict(String id) {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<DistrictsResponse> districtsResponseCall = api.districtsResponseCall(id);

        districtsResponseCall.enqueue(new Callback<DistrictsResponse>() {
            @Override
            public void onResponse(Call<DistrictsResponse> call, Response<DistrictsResponse> response) {
                districtsArrayList = response.body().getData().getDistricts();
                setDistrictSearchableSpinner(districtsArrayList);
            }

            @Override
            public void onFailure(Call<DistrictsResponse> call, Throwable t) {

            }
        });
    }

    private void getVillage(String id) {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<VillagesResponse> villagesResponseCall = api.villagesResponseCall(id);

        villagesResponseCall.enqueue(new Callback<VillagesResponse>() {
            @Override
            public void onResponse(Call<VillagesResponse> call, Response<VillagesResponse> response) {
                villagesArrayList = response.body().getData().getVillages();
                setVillageSearchableSpinner(villagesArrayList);
            }

            @Override
            public void onFailure(Call<VillagesResponse> call, Throwable t) {

            }
        });
    }

    private void getGroup() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<GroupResponse> groupResponseCall = api.groupsResponseCall();

        groupResponseCall.enqueue(new Callback<GroupResponse>() {
            @Override
            public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                groupArrayList = response.body().getData().getGroups();
                setGroupSearchableSpinner(groupArrayList);
            }

            @Override
            public void onFailure(Call<GroupResponse> call, Throwable t) {

            }
        });
    }

    private void getPosition() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<PositionResponse> positionResponseCall = api.positionsResponseCall();

        positionResponseCall.enqueue(new Callback<PositionResponse>() {
            @Override
            public void onResponse(Call<PositionResponse> call, Response<PositionResponse> response) {
                positionArrayList = response.body().getData().getPositions();
                setPositionSearchableSpinner(positionArrayList);
            }

            @Override
            public void onFailure(Call<PositionResponse> call, Throwable t) {

            }
        });
    }

    private void getEducation() {
        BapelkesPelitaApi api = ClientHelper.getClient().create(BapelkesPelitaApi.class);
        Call<EducationResponse> educationResponseCall = api.educationResponseCall();

        educationResponseCall.enqueue(new Callback<EducationResponse>() {
            @Override
            public void onResponse(Call<EducationResponse> call, Response<EducationResponse> response) {
                educationArrayList = response.body().getData().getEducations();
                setEducationSearchableSpinner(educationArrayList);
            }

            @Override
            public void onFailure(Call<EducationResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (Activity.RESULT_OK == resultCode) {
            if (requestCode == CAMERA_CODE_REQUEST) {
                btnKtp.setText("Gambar yang dipilih : "+PickerManager.INSTANCE.getSelectedPhotos().size());
                photos.addAll(PickerManager.INSTANCE.getSelectedPhotos());
                Log.d("_file_",PickerManager.INSTANCE.getSelectedPhotos().toString());
            } else if (requestCode == FILE_CODE_REQUEST) {
                btnDocument.setText("Dokumen yang dipilih : "+PickerManager.INSTANCE.getSelectedFiles().size());
                documents.addAll(PickerManager.INSTANCE.getSelectedFiles());
                Log.d("_file_",PickerManager.INSTANCE.getSelectedFiles().toString());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_CAMERA_AND_STORAGE)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "Izin Menggunakan Kamera dan Mengakses Folder",
                    RC_CAMERA_AND_STORAGE, perms);
        }
    }
}
