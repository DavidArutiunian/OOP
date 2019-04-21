#include "CPoliceCar.h"

CPoliceCar::CPoliceCar(size_t placeCount, ECarModel carModel)
    : CCarImpl(placeCount, carModel)
{
}

CPoliceCar::~CPoliceCar() = default;
