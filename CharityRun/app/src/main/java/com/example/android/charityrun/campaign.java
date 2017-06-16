package com.example.android.charityrun;

/**
 * Created by HP_PC on 15-06-2017.
 */
public class campaign {
    String name,description,run_count,total;

    campaign(String name,String description,String run_count,String total)
    {
        this.name=name;
        this.description=description;
        this.run_count=run_count;
        this.total=total;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRun_count() {
        return run_count;
    }

    public void setRun_count(String run_count) {
        this.run_count = run_count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
