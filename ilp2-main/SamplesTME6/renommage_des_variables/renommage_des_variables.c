#include <stdio.h> 
#include <stdlib.h> 
#include "ilp.h" 

/* Global variables */ 
ILP_Object f;
ILP_Object x;

/* Global prototypes */ 
ILP_Object ilp__f(ILP_Closure ilp_useless
,
    ILP_Object x_21);

/* Global functions */ 

ILP_Object ilp__f(ILP_Closure ilp_useless
,
    ILP_Object x_21) {
{ 
  ILP_Object ilptmp1; 
{ 
  ILP_Object ilptmp2; 
  ILP_Object ilptmp3; 
ilptmp2 = x_21; 
ilptmp3 = ILP_Integer2ILP(2); 
ilptmp1 = ILP_Plus(ilptmp2, ilptmp3);
} 

  {
    ILP_Object x_32 = ilptmp1;
{ 
  ILP_Object ilptmp4; 
  ILP_Object ilptmp5; 
ilptmp4 = ILP_Integer2ILP(2); 
ilptmp5 = x_32; 
return ILP_Times(ilptmp4, ilptmp5);
} 

  }
}
}
struct ILP_Closure f_closure_object = { 
   &ILP_object_Closure_class, 
   { { ilp__f, 
       1, 
       { NULL } } } 
}; 


ILP_Object ilp_program () 
{ 
{ 
  ILP_Object ilptmp6; 
{ 
  ILP_Object ilptmp7; 
ilptmp7 = ILP_Integer2ILP(12); 
ilptmp6 = (x = ilptmp7); 
} 
{ 
  ILP_Object ilptmp8; 
{ 
  ILP_Object ilptmp9; 
  ILP_Object ilptmp10; 
ilptmp9 = ILP_Integer2ILP(3); 
ilptmp10 = x; 
ilptmp8 = ILP_Times(ilptmp9, ilptmp10);
} 

  {
    ILP_Object x_13 = ilptmp8;
{ 
  ILP_Object ilptmp11; 
{ 
  ILP_Object ilptmp12; 
  ILP_Object ilptmp13; 
ilptmp12 = ILP_Integer2ILP(3); 
ilptmp13 = x_13; 
ilptmp11 = ILP_Times(ilptmp12, ilptmp13);
} 
ilptmp6 = ilp__f(NULL , ilptmp11);
}

  }
}
return ilptmp6; 
} 

} 

static ILP_Object ilp_caught_program () {
  struct ILP_catcher* current_catcher = ILP_current_catcher;
  struct ILP_catcher new_catcher;

  if ( 0 == setjmp(new_catcher._jmp_buf) ) {
    ILP_establish_catcher(&new_catcher);
    return ilp_program();
  };
  return ILP_current_exception;
}

int main (int argc, char *argv[]) 
{ 
  ILP_START_GC; 
  ILP_print(ilp_caught_program()); 
  ILP_newline(); 
  return EXIT_SUCCESS; 
} 
