package com.example.agendaunsada2.Viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agendaunsada2.Model.CarrerPlan;
import com.example.agendaunsada2.Service.CarrerPlanService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarrerPlanViewModel extends ViewModel {
    private final CarrerPlanService carrerPlanService;
    private final MutableLiveData<List<String>> carrerPlanNames; // Nombres para mostrar en el Spinner
    private final MutableLiveData<List<CarrerPlan>> carrerPlans; // Lista completa de objetos CarrerPlan
    private final MutableLiveData<CarrerPlan> selectedCarrerPlan; // Plan seleccionado por el usuario
    private final MutableLiveData<String> errorMessage;
    private final MutableLiveData<Boolean> isLoading;

    public CarrerPlanViewModel() {
        carrerPlanService = new CarrerPlanService();
        carrerPlanNames = new MutableLiveData<>(new ArrayList<>());
        carrerPlans = new MutableLiveData<>(new ArrayList<>());
        selectedCarrerPlan = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        isLoading = new MutableLiveData<>(false);
    }

    public LiveData<List<String>> getCarrerPlanNames() {
        return carrerPlanNames;
    }

    public LiveData<List<CarrerPlan>> getCarrerPlans() {
        return carrerPlans;
    }

    public LiveData<CarrerPlan> getSelectedCarrerPlan() {
        return selectedCarrerPlan;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    // Método para obtener todos los planes de carrera
    public void fetchCarrerPlans() {
        isLoading.setValue(true);
        carrerPlanService.getAllCarrerPlans().enqueue(new Callback<List<CarrerPlan>>() {
            @Override
            public void onResponse(Call<List<CarrerPlan>> call, Response<List<CarrerPlan>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    List<CarrerPlan> plans = response.body();
                    carrerPlans.setValue(plans);

                    // Extraer los nombres para el Spinner
                    List<String> planNames = new ArrayList<>();
                    planNames.add("Seleccionar"); // Default option
                    for (CarrerPlan plan : plans) {
                        planNames.add(plan.getName());
                    }
                    carrerPlanNames.setValue(planNames);
                } else {
                    errorMessage.setValue("Error al obtener los planes de carrera: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<CarrerPlan>> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }

    // Método para seleccionar un plan de carrera
    public void selectCarrerPlan(CarrerPlan carrerPlan) {
        selectedCarrerPlan.setValue(carrerPlan);
    }

    // Método para manejar errores
    public void clearErrorMessage() {
        errorMessage.setValue(null);
    }
}
