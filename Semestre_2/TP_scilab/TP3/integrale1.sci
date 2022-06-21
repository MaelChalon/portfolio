function [x,y]=encadrement_integrale1(a,b,f)
    x=f(a)*(b-a);
    y=f(b)*(b-a);
    if x>y
        tmp=x;
        x=y;
        y=tmp;
    end
endfunction
