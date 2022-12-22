#include <stdio.h> 
#include <stdlib.h> 
#include "ilp.h" 

/* Global variables */ 
ILP_Object f;
ILP_Object z;

/* Global prototypes */ 
ILP_Object ilp__f(ILP_Closure ilp_useless
,
    ILP_Object x1);

/* Global functions */ 

ILP_Object ilp__f(ILP_Closure ilp_useless
,
    ILP_Object x1) {
{ 
  ILP_Object ilptmp1; 
  ILP_Object ilptmp2; 
ilptmp1 = x1; 
ilptmp2 = z; 
return ILP_Plus(ilptmp1, ilptmp2);
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
  ILP_Object ilptmp3; 
{ 
  ILP_Object ilptmp4; 
ilptmp4 = ILP_Integer2ILP(2); 
ilptmp3 = (z = ilptmp4); 
} 
{ 
  ILP_Object ilptmp5; 
ilptmp5 = ILP_Integer2ILP(12); 

  {
    ILP_Object z2 = ilptmp5;
{ 
  ILP_Object ilptmp6; 
ilptmp6 = z2; 
ilptmp3 = ilp__f(NULL , ilptmp6);
}

  }
}
return ilptmp3; 
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
