package com.example.myapplication.controller.PopUp.abstractpopup;

import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.model.EntityEmployee;

/**
 * Created by Benjamin Vouillon on 28,January,2020
 */
public abstract class DeleteEmployeePopUp extends DeletePopUp {
    EntityEmployee mSelectedEmployee;

    public DeleteEmployeePopUp(EntityEmployee entityEmployee, ButtonPanel activity, EntityEmployee selectedEmployee) {
        super(entityEmployee, activity);
        this.mSelectedEmployee = selectedEmployee;
    }

    @Override
    protected void onDelete() {
        this.mEmployeeDAO.deleteEmployeeById(this.mSelectedEmployee.getIdEmployee());
    }
}
