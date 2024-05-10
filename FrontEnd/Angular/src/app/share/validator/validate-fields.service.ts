import { Injectable } from '@angular/core';
import { FormArray, FormControl, FormGroup, UntypedFormControl, UntypedFormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ValidateFieldsService {
  isValid=true;

  constructor() { }


  isValidated(form:UntypedFormGroup){



    Object.keys(form.controls).forEach((x)=>{
      //console.log(x)
      const field=form.get(x) as UntypedFormControl

      //console.log(x,field)
      if(field instanceof FormControl){

         if(field.errors){
          console.log(this.isValid)
          this.isValid=false
          console.log(this.isValid)

         }else{
          console.log("senao")
          //this.isValid=true
         }
      }else {
        this.isValidated(field)
      }

    })
    return this.isValid
  }


  formControlError(form:FormGroup,field:string){
    const control=form.get(field) as FormControl
    return this.validatorFormControl(control)
  }

  formArrayError(form:FormGroup,formArray:string,index:number,field:string){
    const formArrayGroup=form.get(formArray) as FormGroup
    const formControlOfArray=formArrayGroup.controls[index].get(field) as FormControl
    return this.validatorFormControl(formControlOfArray)
  }

    validatorFormControl(form:FormControl):String{
     const requestedField= form

      if(requestedField?.hasError("required")){
        //console.log("é requerido")
        return 'Campo Obrigatório'

      }

      if(requestedField?.hasError('minlength')){
        const requiredLength= requestedField.errors ? requestedField.errors['minlength']['requiredLength'] : '3';
        return `Número minimo de ${requiredLength} caracteres`
      }

      if(requestedField?.hasError('maxlength')){
        const requiredLength= requestedField.errors ? requestedField.errors['maxlength']['requiredLength'] : '100';
        return `Número máximo de ${requiredLength} caracteres excedido`
      }

      if(requestedField?.hasError("min")){
        const requiredMin= requestedField.errors ? requestedField.errors['min']['min'] : 0.1;
        return `Valor minimo de ${requiredMin} para esse campo.`
      }

      return "nada"



    }


}
