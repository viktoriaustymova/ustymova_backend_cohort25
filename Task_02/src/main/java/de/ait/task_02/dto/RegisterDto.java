package de.ait.task_02.dto;

public class RegisterDto {
    private String inputEventName;

    private String inputEventDesc;

    public RegisterDto(String inputEventName, String inputEventDesc) {
        this.inputEventName = inputEventName;
        this.inputEventDesc = inputEventDesc;
    }

    public RegisterDto() {
    }

    public String getInputEventName() {
        return inputEventName;
    }

    public void setInputEventName(String inputEventName) {
        this.inputEventName = inputEventName;
    }

    public String getInputEventDesc() {
        return inputEventDesc;
    }

    public void setInputEventDesc(String inputEventDesc) {
        this.inputEventDesc = inputEventDesc;
    }
}
