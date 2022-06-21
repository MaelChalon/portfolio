function [x,n]=dichotomie2(a,b,f,e)
    n=0;
    while abs(a-b)>= e
        n=n+1;
        x= (b+a)/2;
        if f(a)*f(x)<0
            b=(b+a)/2;
        else
            a = (b+a)/2;
        end
    end
endfunction
