const helloSupplier = () => () => "Hello";
const isEmptyPredicate = () => str => str.length === 0;
const stringMultiplier = () => (string, times) => string.repeat(times);
const toDollarStringFunction = () => bigDecimal => "$" + bigDecimal.toString();
const lengthInRangePredicate = (min, max) => str => str.length() >= min && str.length() < max;
const randomIntSupplier = () => Math.ceil(Math.random() * 100)
const boundedRandomIntSupplier = () => (max) => Math.ceil(Math.random() * max )
const intSquareOperation = () => value => value * value
const longSumOperation = () => (value1, value2) => value1 + value2
const stringToIntConverter = () => str => parseInt(str)
const nMultiplyFunctionSupplier = n => () => (value => value * n)
const composeWithTrimFunction = fn => fn(str => str.trim())

class Runnable {
  start() {
    console.log("started");
  }

  run() {
    console.log("running");
  }
}

class Thread extends Runnable {
  runnable;
  constructor(runnable) {
    super();
    this.runnable = runnable;
  }

  start() {
    this.runnable.start();
  }

  run() {
    this.runnable.run();
  }
}

const runningThreadSupplier = runnable => () => {
  const thread = new Thread(runnable);
  runnable.run();
  return thread;
}

const newThreadRunnableConsumer = () => runnable => new Thread(runnable).start();

const runnableToThreadSupplierFunction = () => runnable => () => {
  const thread = new Thread(runnable);
  thread.start();
  return thread;
}

const functionToConditionalFunction = () => (intUnaryOperator, intPredicate) =>
    value => intPredicate(value) ? intUnaryOperator(value) : value

const functionLoader = () => (map, str) => ((operand) => map.has(str) ? map.get(str)(operand) :
  (operand => operand)(operand));

const trickyWellDoneSupplier = () => () => () => "WELL DONE!"


