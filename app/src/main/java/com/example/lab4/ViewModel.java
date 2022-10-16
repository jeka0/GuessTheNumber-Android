package com.example.lab4;

import android.view.View;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private int comp_num;
    private int maxAttempts;
    private int count;
    private int massId, butId,baseMassId;
    private String Name, hintColor;
    public ViewModel()
    {
        comp_num = GuessNum.rnd_comp_num();
        maxAttempts=5;
        count = maxAttempts;
        massId = R.string.show_hint_label;
        baseMassId = R.string.show_hint_label;
        hintColor = "#C83737";
        butId = R.string.button_guess_label;
        Name = "Суперигрок";
    }

    public void setName(String name) {
        Name = name;
    }

    public void setButId(int butId) {
        this.butId = butId;
    }

    public void setMassId(int mass) {
        this.massId = mass;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setComp_num(int comp_num) {
        this.comp_num = comp_num;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public String getName() {
        return Name;
    }

    public int getButId() {
        return butId;
    }

    public int getCount() {
        return count;
    }

    public int getMassId() {
        return massId;
    }

    public int getComp_num() {
        return comp_num;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getBaseMassId() {
        return baseMassId;
    }

    public void setBaseMassId(int baseMassId) {
        this.baseMassId = baseMassId;
    }

    public String getHintColor() {
        return hintColor;
    }

    public void setHintColor(String hintColor) {
        this.hintColor = hintColor;
    }
}
