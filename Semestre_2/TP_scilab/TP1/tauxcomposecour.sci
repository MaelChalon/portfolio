function [s,te]=tauxcomposecourt(p,r,i,n)
    s=p;
    for j=0:i*n-1
        s=((s*r/n)/100)+s;
    end
    te=0;
    
endfunction
