function [x,y]=encadrement_integrale2(a,b,f,m)
    p=(b-a)/m;
    x=0;
    y=0;
    for i=a:p:b-p
        x=x+f(i+p)*p;
        y=y+f(i)*p;
    end
    if x>y
        tmp=x;
        x=y;
        y=tmp;
    end
endfunction
