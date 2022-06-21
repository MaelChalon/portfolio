for i=0:4
    disp(string (trapeze(0,1,2^i,f1)));
    disp(string (log(2) - trapeze(0,1,2^i,f1)));
end
