// #Sireum #Logika
//@Logika: --manual --background disabled
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._


//YOU DO THIS:
//Add proof blocks so the asserts at the end hold.

//Do not change anything in the program or asserts.


var numWheels: Z = Z.read()
var numBikes: Z = Z.read()
var numCars: Z = Z.read()

val oldBikes: Z = numBikes
val oldCars: Z = numCars

//INVARIANT: numWheels == numBikes*2 + numCars*4
assume(numWheels == numBikes*2 + numCars*4)
Deduce(
  1 ( numWheels == numBikes*2 + numCars*4) by Premise,
)
var addVehicle: Z = Z.prompt("Enter 1 to add a bike, and 2 to add a car: ")

if (addVehicle == 1) {
  numBikes = numBikes + 1
  Deduce(
    1 ( numBikes == Old(numBikes) + 1 ) by Premise,
    2 ( oldBikes == Old(numBikes) ) by Premise,
    44 ( numBikes == oldBikes + 1 ) by Subst_>(2,1),
    3 ( numWheels == Old(numBikes)*2 + numCars*4 ) by Premise,
    33 ( numBikes - 1 == Old(numBikes) ) by Algebra*(1),
    4 ( numWheels == (numBikes - 1 )*2 + numCars*4 ) by Subst_>(33,3),
    5  ( numWheels == numBikes*2 - 2 + numCars*4 ) by Algebra*(4)
  )
  numWheels = numWheels + 2

  Deduce(
    1 ( numBikes == oldBikes + 1 ) by Premise,
    2 ( Old(numWheels) == numBikes*2 - 2 + numCars*4 ) by Premise,
    3 ( numWheels == Old(numWheels) + 2 ) by Premise,
    4 ( numWheels == numBikes*2 - 2 + numCars*4 + 2 ) by Subst_<(2,3),
    5 ( numWheels == numBikes*2 + numCars*4 ) by Algebra*(4),
    6 ( addVehicle == 1 ) by Premise,
    7 ( 1 != 2 ) by Algebra*(),
    8 ( addVehicle != 2 ) by Subst_>(6,7)
  )
} else {
  if (addVehicle == 2) {
    numCars = numCars + 1
    Deduce(
      1 ( numCars == Old(numCars) + 1 ) by Premise,
      2 ( oldCars == Old(numCars) ) by Premise,
      22 ( numCars == oldCars + 1 ) by Subst_>(2,1),
      3 ( numWheels == numBikes*2 + Old(numCars)*4 ) by Premise,
      33 ( numCars - 1 == Old(numCars) ) by Algebra*(1),
      4 ( numWheels == numBikes*2 + (numCars - 1)*4 ) by Subst_>(33,3),
      5  ( numWheels == numBikes*2 + numCars*4 - 4 ) by Algebra*(4)
    )

      numWheels = numWheels + 4
    Deduce(
      1 ( numCars == oldCars + 1 ) by Premise,
      2 ( Old(numWheels) == numBikes*2 + numCars*4 - 4 ) by Premise,
      3 ( numWheels == Old(numWheels) + 4 ) by Premise,
      4 ( numWheels == numBikes*2 + numCars*4 - 4 + 4 ) by Subst_<(2,3),
      5 ( numWheels == numBikes*2 + numCars*4 ) by Algebra*(4),
      6 ( !(addVehicle == 1) ) by Premise,
      7 ( addVehicle != 1 ) by Algebra*(6)
    )
  } else {
    println("Error: input should be 1 or 2")
    Deduce(
      1 ( numWheels == numBikes*2 + numCars*4) by Premise,
      2 ( !(addVehicle == 1 )) by Premise,
      3 ( !(addVehicle == 2 )) by Premise,
      4 ( addVehicle != 1 ) by Algebra*(2),
      5 ( addVehicle != 2 ) by Algebra*(3),
    )
  }
  Deduce(
    1 ( !(addVehicle == 1 )) by Premise,
    3 ( addVehicle != 1 ) by Algebra*(1),
    2 ( numWheels == numBikes*2 + numCars*4) by Premise,
    
  )
}

Deduce(
  1 ( numWheels == numBikes*2 + numCars*4) by Premise,
  2 ( addVehicle != 1 | numBikes == oldBikes + 1 ) by Premise,
  3 ( addVehicle != 2 | numCars == oldCars + 1 ) by Premise,
)
//invariant still holds
assert(numWheels == numBikes*2 + numCars*4)

//either they didn't select to add a bike, or I now have one more bike
assert(addVehicle != 1 | numBikes == oldBikes + 1)

//either they didn't select to add a car, or I now have one more car
assert(addVehicle != 2 | numCars == oldCars + 1)